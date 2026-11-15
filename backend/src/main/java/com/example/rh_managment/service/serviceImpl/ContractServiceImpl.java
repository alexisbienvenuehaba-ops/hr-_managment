package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.ContractRequestDto;
import com.example.rh_managment.dto.response.ContractResponseDto;
import com.example.rh_managment.entity.ContractEntity;
import com.example.rh_managment.entity.EmployeeEntity;
import com.example.rh_managment.enums.ContractStatus;
import com.example.rh_managment.enums.ContractType;
import com.example.rh_managment.enums.EmployeeStatus;
import com.example.rh_managment.mapper.ContractMapper;
import com.example.rh_managment.repository.ContractRepository;
import com.example.rh_managment.repository.EmployeeRepository;
import com.example.rh_managment.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractRepository repository;
    private final EmployeeRepository employeeRepository;
    private final ContractMapper mapper;

    @Override
    public ContractResponseDto create(ContractRequestDto dto) {
        EmployeeEntity employee = employeeRepository.findById(dto.getIdEmployee()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        if (employee.getEmployeeStatus() != EmployeeStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This employee has left the company");
        }
        if (repository.existsByReferenceContractIgnoreCase(dto.getReferenceContract().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A contract with this reference already exists");
        }
        validateDates(dto);
        checkNoOverlap(employee.getIdEmployee(), dto.getStartDateContract(), dto.getEndDateContract(), null);

        return mapper.toResponse(repository.save(mapper.toEntity(dto, employee)));
    }

    @Override
    public ContractResponseDto update(Long id, ContractRequestDto dto) {
        ContractEntity e = getOrThrow(id);
        if (e.getStatusContract() != ContractStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Only an active contract can be modified");
        }
        if (!e.getEmployee().getIdEmployee().equals(dto.getIdEmployee())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The employee of a contract cannot be changed");
        }
        if (repository.existsByReferenceContractIgnoreCaseAndIdContractNot(dto.getReferenceContract().trim(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A contract with this reference already exists");
        }
        validateDates(dto);
        checkNoOverlap(e.getEmployee().getIdEmployee(), dto.getStartDateContract(), dto.getEndDateContract(), id);

        mapper.updateEntity(e, dto);
        return mapper.toResponse(repository.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public ContractResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractResponseDto> findByEmployee(Long idEmployee) {
        return repository.findByEmployee_IdEmployeeOrderByStartDateContractDesc(idEmployee).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ContractResponseDto terminate(Long id) {
        ContractEntity e = getOrThrow(id);
        if (e.getStatusContract() != ContractStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Only an active contract can be terminated");
        }
        LocalDate today = LocalDate.now();
        e.setStatusContract(ContractStatus.TERMINATED);
        // La fin anticipée ramène la date de fin à aujourd'hui (jamais avant le début)
        if (e.getEndDateContract() == null || e.getEndDateContract().isAfter(today)) {
            e.setEndDateContract(today.isBefore(e.getStartDateContract()) ? e.getStartDateContract() : today);
        }
        return mapper.toResponse(repository.save(e));
    }

    // CDI : pas de date de fin ; CDD et stage : date de fin obligatoire, après le début
    private void validateDates(ContractRequestDto dto) {
        LocalDate start = dto.getStartDateContract();
        LocalDate end = dto.getEndDateContract();
        if (dto.getTypeContract() == ContractType.PERMANENT) {
            if (end != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "A permanent contract cannot have an end date");
            }
            return;
        }
        if (end == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "An end date is required for a fixed-term contract or an internship");
        }
        if (end.isBefore(start)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The end date cannot be before the start date");
        }
    }

    // Deux périodes se chevauchent si chacune commence avant la fin de l'autre
    private void checkNoOverlap(Long idEmployee, LocalDate start, LocalDate end, Long excludedId) {
        for (ContractEntity c : repository.findByEmployee_IdEmployee(idEmployee)) {
            if (excludedId != null && c.getIdContract().equals(excludedId)) continue;
            boolean startsBeforeOtherEnds =
                    c.getEndDateContract() == null || !start.isAfter(c.getEndDateContract());
            boolean endsAfterOtherStarts =
                    end == null || !end.isBefore(c.getStartDateContract());
            if (startsBeforeOtherEnds && endsAfterOtherStarts) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "This period overlaps another contract of the same employee ("
                                + c.getReferenceContract() + ")");
            }
        }
    }

    private ContractEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Contract not found"));
    }
}