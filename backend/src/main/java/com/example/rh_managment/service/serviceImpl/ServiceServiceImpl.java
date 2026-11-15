package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.ServiceRequestDto;
import com.example.rh_managment.dto.response.ServiceResponseDto;
import com.example.rh_managment.entity.DepartmentEntity;
import com.example.rh_managment.entity.ServiceEntity;
import com.example.rh_managment.enums.DepartmentStatus;
import com.example.rh_managment.enums.ServiceStatus;
import com.example.rh_managment.mapper.ServiceMapper;
import com.example.rh_managment.repository.DepartmentRepository;
import com.example.rh_managment.repository.ServiceRepository;
import com.example.rh_managment.service.ServiceAssignmentHistoryService;
import com.example.rh_managment.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository repository;
    private final DepartmentRepository departmentRepository;
    private final ServiceMapper mapper;
    private final ServiceAssignmentHistoryService historyService;

    @Override
    public ServiceResponseDto create(ServiceRequestDto dto) {
        if (repository.existsByNameServiceIgnoreCase(dto.getNameService().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A service with this name already exists");
        }
        DepartmentEntity department = getActiveDepartment(dto.getIdDepartment());
        ServiceEntity saved = repository.save(mapper.toEntity(dto, department));
        historyService.openInitial(saved, department);
        return mapper.toResponse(saved);
    }

    @Override
    public ServiceResponseDto update(Long id, ServiceRequestDto dto) {
        ServiceEntity e = getOrThrow(id);
        if (repository.existsByNameServiceIgnoreCaseAndIdServiceNot(dto.getNameService().trim(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A service with this name already exists");
        }
        DepartmentEntity department = e.getDepartment();
        boolean departmentChanged = !department.getIdDepartment().equals(dto.getIdDepartment());
        // Si le département change, il doit exister et être actif
        if (departmentChanged) {
            department = getActiveDepartment(dto.getIdDepartment());
        }
        mapper.updateEntity(e, dto, department);
        ServiceEntity saved = repository.saveAndFlush(e);
        if (departmentChanged) {
            historyService.changeDepartment(saved, department);
        }
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceResponseDto> findAllActive() {
        return repository.findAll().stream()
                .filter(s -> s.getStatusService() == ServiceStatus.ACTIVE)
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceResponseDto> findByDepartment(Long idDepartment) {
        return repository.findByDepartment_IdDepartment(idDepartment).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void deactivate(Long id) {
        ServiceEntity e = getOrThrow(id);
        e.setStatusService(ServiceStatus.INACTIVE);
        repository.save(e);
    }

    @Override
    public void activate(Long id) {
        ServiceEntity e = getOrThrow(id);
        e.setStatusService(ServiceStatus.ACTIVE);
        repository.save(e);
    }

    private DepartmentEntity getActiveDepartment(Long idDepartment) {
        DepartmentEntity department = departmentRepository.findById(idDepartment).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
        if (department.getStatus() != DepartmentStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Cannot attach a service to an inactive department");
        }
        return department;
    }

    private ServiceEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
    }
}