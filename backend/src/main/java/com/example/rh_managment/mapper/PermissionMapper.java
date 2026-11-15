package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.PermissionRequestDto;
import com.example.rh_managment.dto.response.PermissionResponseDto;
import com.example.rh_managment.entity.PermissionEntity;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public PermissionEntity toEntity(PermissionRequestDto dto) {
        PermissionEntity e = new PermissionEntity();
        updateEntity(e, dto);
        return e;
    }

    public void updateEntity(PermissionEntity e, PermissionRequestDto dto) {
        // Les codes sont enregistrés en majuscules (ex. EMPLOYEE_READ)
        e.setCodePermission(dto.getCodePermission().trim().toUpperCase());
        e.setDescriptionPermission(
                dto.getDescriptionPermission() == null ? null : dto.getDescriptionPermission().trim());
    }

    public PermissionResponseDto toResponse(PermissionEntity e) {
        PermissionResponseDto r = new PermissionResponseDto();
        r.setIdPermission(e.getIdPermission());
        r.setCodePermission(e.getCodePermission());
        r.setDescriptionPermission(e.getDescriptionPermission());
        return r;
    }
}