package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.DepartmentRequestDto;
import com.example.rh_managment.dto.response.DepartmentResponseDto;
import com.example.rh_managment.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentEntity toEntity(DepartmentRequestDto dto) {
        DepartmentEntity e = new DepartmentEntity();
        updateEntity(e, dto);
        return e;
    }

    public void updateEntity(DepartmentEntity e, DepartmentRequestDto dto) {
        e.setName(dto.getName().trim());
        e.setDescription(dto.getDescription() == null ? null : dto.getDescription().trim());
    }

    public DepartmentResponseDto toResponse(DepartmentEntity e) {
        DepartmentResponseDto r = new DepartmentResponseDto();
        r.setIdDepartment(e.getIdDepartment());
        r.setName(e.getName());
        r.setDescription(e.getDescription());
        r.setStatus(e.getStatus());
        r.setCreatedAt(e.getCreatedAt());
        r.setUpdatedAt(e.getUpdatedAt());
        return r;
    }
}