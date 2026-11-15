package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.response.ServiceAssignmentHistoryResponseDto;
import com.example.rh_managment.entity.DepartmentEntity;
import com.example.rh_managment.entity.ServiceAssignmentHistoryEntity;
import com.example.rh_managment.entity.ServiceEntity;
import com.example.rh_managment.mapper.ServiceAssignmentHistoryMapper;
import com.example.rh_managment.repository.ServiceAssignmentHistoryRepository;
import com.example.rh_managment.service.ServiceAssignmentHistoryService;
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
public class ServiceAssignmentHistoryServiceImpl implements ServiceAssignmentHistoryService {

    private final ServiceAssignmentHistoryRepository repository;
    private final ServiceAssignmentHistoryMapper mapper;

    @Override
    public void openInitial(ServiceEntity service, DepartmentEntity department) {
        repository.save(newLine(service, department));
    }

    @Override
    public void changeDepartment(ServiceEntity service, DepartmentEntity newDepartment) {
        // On clôture le rattachement courant (s'il existe), puis on en ouvre un nouveau
        repository.findByService_IdServiceAndEndDateHistoryIsNull(service.getIdService())
                .ifPresent(current -> {
                    current.setEndDateHistory(LocalDate.now());
                    repository.save(current);
                });
        repository.save(newLine(service, newDepartment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceAssignmentHistoryResponseDto> findByService(Long idService) {
        return repository.findByService_IdServiceOrderByStartDateHistoryDesc(idService).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceAssignmentHistoryResponseDto findCurrentByService(Long idService) {
        return repository.findByService_IdServiceAndEndDateHistoryIsNull(idService)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No current department assignment for this service"));
    }

    private ServiceAssignmentHistoryEntity newLine(ServiceEntity service, DepartmentEntity department) {
        ServiceAssignmentHistoryEntity h = new ServiceAssignmentHistoryEntity();
        h.setService(service);
        h.setDepartment(department);
        h.setStartDateHistory(LocalDate.now());
        return h;
    }
}