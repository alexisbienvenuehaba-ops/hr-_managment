package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.PermissionRequestDto;
import com.example.rh_managment.dto.response.PermissionResponseDto;
import com.example.rh_managment.entity.PermissionEntity;
import com.example.rh_managment.mapper.PermissionMapper;
import com.example.rh_managment.repository.PermissionRepository;
import com.example.rh_managment.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repository;
    private final PermissionMapper mapper;

    @Override
    public PermissionResponseDto create(PermissionRequestDto dto) {
        if (repository.existsByCodePermissionIgnoreCase(dto.getCodePermission().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A permission with this code already exists");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public PermissionResponseDto update(Long id, PermissionRequestDto dto) {
        PermissionEntity e = getOrThrow(id);
        if (repository.existsByCodePermissionIgnoreCaseAndIdPermissionNot(dto.getCodePermission().trim(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A permission with this code already exists");
        }
        mapper.updateEntity(e, dto);
        return mapper.toResponse(repository.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    private PermissionEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found"));
    }
}