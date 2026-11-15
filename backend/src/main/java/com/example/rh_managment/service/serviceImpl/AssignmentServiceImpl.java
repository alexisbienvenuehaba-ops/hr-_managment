package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.AssignmentRequestDto;
import com.example.rh_managment.dto.response.AssignmentResponseDto;
import com.example.rh_managment.entity.AssignmentEntity;
import com.example.rh_managment.entity.EmployeeEntity;
import com.example.rh_managment.entity.PositionEntity;
import com.example.rh_managment.entity.ServiceEntity;
import com.example.rh_managment.enums.DepartmentStatus;
import com.example.rh_managment.enums.EmployeeStatus;
import com.example.rh_managment.enums.PositionStatus;
import com.example.rh_managment.enums.ServiceStatus;
import com.example.rh_managment.mapper.AssignmentMapper;
import com.example.rh_managment.repository.AssignmentRepository;
import com.example.rh_managment.repository.EmployeeRepository;
import com.example.rh_managment.repository.PositionRepository;
import com.example.rh_managment.repository.ServiceRepository;
import com.example.rh_managment.service.AssignmentService;
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
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository repository;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ServiceRepository serviceRepository;
    private final AssignmentMapper mapper;

    @Override
    public AssignmentResponseDto assign(AssignmentRequestDto dto) {
        EmployeeEntity employee = employeeRepository.findById(dto.getIdEmployee()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        if (employee.getEmployeeStatus() != EmployeeStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This employee has left the company");
        }

        PositionEntity position = positionRepository.findById(dto.getIdPosition()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Position not found"));
        if (position.getStatus() != PositionStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This position is inactive");
        }

        ServiceEntity service = serviceRepository.findById(dto.getIdService()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
        if (service.getStatusService() != ServiceStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This service is inactive");
        }
        if (service.getDepartment().getStatus() != DepartmentStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The department of this service is inactive");
        }

        // On clôture l'affectation courante (s'il y en a une), puis on crée la nouvelle
        repository.findByEmployee_IdEmployeeAndEndDateAssignmentIsNull(employee.getIdEmployee())
                .ifPresent(current -> {
                    boolean same = current.getPosition().getIdPosition().equals(position.getIdPosition())
                            && current.getService().getIdService().equals(service.getIdService());
                    if (same) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT,
                                "The employee already has this position in this service");
                    }
                    current.setEndDateAssignment(LocalDate.now());
                    repository.save(current);
                });

        AssignmentEntity saved = repository.save(mapper.toEntity(dto, employee, position, service));
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AssignmentResponseDto findById(Long id) {
        return mapper.toResponse(repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentResponseDto> findByEmployee(Long idEmployee) {
        return repository.findByEmployee_IdEmployeeOrderByStartDateAssignmentDesc(idEmployee).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AssignmentResponseDto findCurrentByEmployee(Long idEmployee) {
        return repository.findByEmployee_IdEmployeeAndEndDateAssignmentIsNull(idEmployee)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No current assignment for this employee"));
    }
}