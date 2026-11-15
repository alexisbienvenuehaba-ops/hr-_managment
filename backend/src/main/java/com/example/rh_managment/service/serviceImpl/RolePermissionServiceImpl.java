package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.RolePermissionRequestDto;
import com.example.rh_managment.dto.response.RolePermissionResponseDto;
import com.example.rh_managment.entity.PermissionEntity;
import com.example.rh_managment.entity.RoleEntity;
import com.example.rh_managment.entity.RolePermissionId;
import com.example.rh_managment.mapper.RolePermissionMapper;
import com.example.rh_managment.repository.PermissionRepository;
import com.example.rh_managment.repository.RolePermissionRepository;
import com.example.rh_managment.repository.RoleRepository;
import com.example.rh_managment.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository repository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionMapper mapper;

    @Override
    public RolePermissionResponseDto assign(RolePermissionRequestDto dto) {
        RoleEntity role = roleRepository.findById(dto.getIdRole()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
        PermissionEntity permission = permissionRepository.findById(dto.getIdPermission()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found"));

        if (repository.existsById(buildId(role.getIdRole(), permission.getIdPermission()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This role already has this permission");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(role, permission)));
    }

    @Override
    public void remove(Long idRole, Long idPermission) {
        RolePermissionId id = buildId(idRole, idPermission);
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This role does not have this permission");
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolePermissionResponseDto> findByRole(Long idRole) {
        return repository.findByRole_IdRole(idRole).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolePermissionResponseDto> findByPermission(Long idPermission) {
        return repository.findByPermission_IdPermission(idPermission).stream().map(mapper::toResponse).toList();
    }

    private RolePermissionId buildId(Long idRole, Long idPermission) {
        RolePermissionId id = new RolePermissionId();
        id.setIdRole(idRole);
        id.setIdPermission(idPermission);
        return id;
    }
}