package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.RoleRequestDto;
import com.example.rh_managment.dto.response.RoleResponseDto;
import com.example.rh_managment.entity.RoleEntity;
import com.example.rh_managment.mapper.RoleMapper;
import com.example.rh_managment.repository.RoleRepository;
import com.example.rh_managment.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public RoleResponseDto create(RoleRequestDto dto) {
        if (repository.existsByNameRoleIgnoreCase(dto.getNameRole().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A role with this name already exists");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public RoleResponseDto update(Long id, RoleRequestDto dto) {
        RoleEntity e = getOrThrow(id);
        if (repository.existsByNameRoleIgnoreCaseAndIdRoleNot(dto.getNameRole().trim(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A role with this name already exists");
        }
        mapper.updateEntity(e, dto);
        return mapper.toResponse(repository.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    private RoleEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }
}