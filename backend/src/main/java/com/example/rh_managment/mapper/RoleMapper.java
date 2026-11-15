package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.RoleRequestDto;
import com.example.rh_managment.dto.response.RoleResponseDto;
import com.example.rh_managment.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleEntity toEntity(RoleRequestDto dto) {
        RoleEntity e = new RoleEntity();
        updateEntity(e, dto);
        return e;
    }

    public void updateEntity(RoleEntity e, RoleRequestDto dto) {
        // Les noms de rôles sont enregistrés en majuscules (ex. RH, ADMINISTRATEUR)
        e.setNameRole(dto.getNameRole().trim().toUpperCase());
        e.setDescriptionRole(dto.getDescriptionRole() == null ? null : dto.getDescriptionRole().trim());
    }

    public RoleResponseDto toResponse(RoleEntity e) {
        RoleResponseDto r = new RoleResponseDto();
        r.setIdRole(e.getIdRole());
        r.setNameRole(e.getNameRole());
        r.setDescriptionRole(e.getDescriptionRole());
        return r;
    }
}