package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.response.RolePermissionResponseDto;
import com.example.rh_managment.entity.PermissionEntity;
import com.example.rh_managment.entity.RoleEntity;
import com.example.rh_managment.entity.RolePermissionEntity;
import com.example.rh_managment.entity.RolePermissionId;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionMapper {

    public RolePermissionEntity toEntity(RoleEntity role, PermissionEntity permission) {
        RolePermissionId id = new RolePermissionId();
        id.setIdRole(role.getIdRole());
        id.setIdPermission(permission.getIdPermission());

        RolePermissionEntity e = new RolePermissionEntity();
        e.setId(id);
        e.setRole(role);
        e.setPermission(permission);
        return e;
    }

    public RolePermissionResponseDto toResponse(RolePermissionEntity e) {
        RolePermissionResponseDto r = new RolePermissionResponseDto();
        r.setIdRole(e.getRole().getIdRole());
        r.setNameRole(e.getRole().getNameRole());
        r.setIdPermission(e.getPermission().getIdPermission());
        r.setCodePermission(e.getPermission().getCodePermission());
        r.setDescriptionPermission(e.getPermission().getDescriptionPermission());
        return r;
    }
}