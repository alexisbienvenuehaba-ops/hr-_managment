package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.AbsenceCancelRequestDto;
import com.example.rh_managment.dto.request.AbsenceDecisionRequestDto;
import com.example.rh_managment.dto.request.AbsenceOpinionRequestDto;
import com.example.rh_managment.dto.request.AbsenceRequestRequestDto;
import com.example.rh_managment.dto.response.AbsenceRequestResponseDto;
import com.example.rh_managment.entity.AbsenceRequestEntity;
import com.example.rh_managment.entity.EmployeeEntity;
import com.example.rh_managment.entity.UserEntity;
import com.example.rh_managment.enums.AbsenceStatus;
import com.example.rh_managment.enums.EmployeeStatus;
import com.example.rh_managment.mapper.AbsenceRequestMapper;
import com.example.rh_managment.repository.AbsenceRequestRepository;
import com.example.rh_managment.repository.EmployeeRepository;
import com.example.rh_managment.repository.UserRepository;
import com.example.rh_managment.service.AbsenceRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AbsenceRequestServiceImpl implements AbsenceRequestService {

    private final AbsenceRequestRepository repository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final AbsenceRequestMapper mapper;

    @Override
    public AbsenceRequestResponseDto create(AbsenceRequestRequestDto dto) {
        EmployeeEntity employee = employeeRepository.findById(dto.getIdEmployee()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        if (employee.getEmployeeStatus() != EmployeeStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This employee has left the company");
        }
        if (!dto.getEndDateAbsence().isAfter(dto.getStartDateAbsence())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The end date must be after the start date");
        }
        // Une demande n'est jamais approuvée automatiquement : elle démarre en attente
        return mapper.toResponse(repository.save(mapper.toEntity(dto, employee)));
    }

    @Override
    public AbsenceRequestResponseDto giveManagerOpinion(Long id, AbsenceOpinionRequestDto dto) {
        AbsenceRequestEntity e = getOrThrow(id);
        requirePending(e);
        e.setManagerOpinionAbsence(dto.getManagerOpinionAbsence().trim());
        return mapper.toResponse(repository.save(e));
    }

    @Override
    public AbsenceRequestResponseDto decide(Long id, AbsenceDecisionRequestDto dto) {
        AbsenceRequestEntity e = getOrThrow(id);
        requirePending(e);

        AbsenceStatus decision = dto.getDecisionAbsence();
        if (decision != AbsenceStatus.APPROVED && decision != AbsenceStatus.REJECTED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The decision must be APPROVED or REJECTED");
        }
        String comment = dto.getHrDecisionAbsence() == null ? "" : dto.getHrDecisionAbsence().trim();
        if (decision == AbsenceStatus.REJECTED && comment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A reason is required to reject a request");
        }

        e.setHandlerUser(getActiveUser(dto.getIdUser()));
        e.setStatusAbsence(decision);
        e.setHrDecisionAbsence(comment.isEmpty() ? null : comment);
        return mapper.toResponse(repository.save(e));
    }

    @Override
    public AbsenceRequestResponseDto cancel(Long id, AbsenceCancelRequestDto dto) {
        AbsenceRequestEntity e = getOrThrow(id);
        boolean byHr = dto != null && dto.getIdUser() != null;

        if (byHr) {
            // Les RH peuvent annuler une demande en attente ou déjà approuvée
            if (e.getStatusAbsence() != AbsenceStatus.PENDING && e.getStatusAbsence() != AbsenceStatus.APPROVED) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Only a pending or approved request can be cancelled");
            }
            e.setHandlerUser(getActiveUser(dto.getIdUser()));
        } else if (e.getStatusAbsence() != AbsenceStatus.PENDING) {
            // L'employé ne peut annuler que sa demande en attente
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Only a pending request can be cancelled by the employee: contact HR");
        }
        e.setStatusAbsence(AbsenceStatus.CANCELLED);
        return mapper.toResponse(repository.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public AbsenceRequestResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbsenceRequestResponseDto> findAll() {
        return repository.findAllByOrderByStartDateAbsenceDesc().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbsenceRequestResponseDto> findByEmployee(Long idEmployee) {
        return repository.findByEmployee_IdEmployeeOrderByStartDateAbsenceDesc(idEmployee).stream()
                .map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbsenceRequestResponseDto> findByStatus(AbsenceStatus status) {
        return repository.findByStatusAbsenceOrderByStartDateAbsenceDesc(status).stream()
                .map(mapper::toResponse).toList();
    }

    private void requirePending(AbsenceRequestEntity e) {
        if (e.getStatusAbsence() != AbsenceStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This request is no longer pending");
        }
    }

    private UserEntity getActiveUser(Long idUser) {
        UserEntity user = userRepository.findById(idUser).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (!Boolean.TRUE.equals(user.getActiveUser())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This user account is inactive");
        }
        return user;
    }

    private AbsenceRequestEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Absence request not found"));
    }
}