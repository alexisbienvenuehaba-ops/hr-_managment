package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.DepartmentRequestDto;
import com.example.rh_managment.dto.response.DepartmentResponseDto;
import com.example.rh_managment.entity.DepartmentEntity;
import com.example.rh_managment.enums.DepartmentStatus;
import com.example.rh_managment.mapper.DepartmentMapper;
import com.example.rh_managment.repository.DepartmentRepository;
import com.example.rh_managment.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Override
    public DepartmentResponseDto create(DepartmentRequestDto dto) {
        if (repository.existsByNameIgnoreCase(dto.getName().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A department with this name already exists");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public DepartmentResponseDto update(Long id, DepartmentRequestDto dto) {
        DepartmentEntity e = getOrThrow(id);
        if (repository.existsByNameIgnoreCaseAndIdDepartmentNot(dto.getName().trim(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A department with this name already exists");
        }
        mapper.updateEntity(e, dto);
        e.setUpdatedAt(LocalDateTime.now());
        return mapper.toResponse(repository.saveAndFlush(e));
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponseDto> findAllActive() {
        return repository.findAll().stream()
                .filter(d -> d.getStatus() == DepartmentStatus.ACTIVE)
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void deactivate(Long id) {
        DepartmentEntity e = getOrThrow(id);
        e.setStatus(DepartmentStatus.INACTIVE);
        e.setUpdatedAt(LocalDateTime.now());
        repository.save(e);
    }

    @Override
    public void activate(Long id) {
        DepartmentEntity e = getOrThrow(id);
        e.setStatus(DepartmentStatus.ACTIVE);
        e.setUpdatedAt(LocalDateTime.now());
        repository.save(e);
    }

    private DepartmentEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
    }
}