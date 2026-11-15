package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.RolePermissionRequestDto;
import com.example.rh_managment.dto.response.RolePermissionResponseDto;

import java.util.List;

public interface RolePermissionService {
    RolePermissionResponseDto assign(RolePermissionRequestDto dto);
    void remove(Long idRole, Long idPermission);
    List<RolePermissionResponseDto> findByRole(Long idRole);
    List<RolePermissionResponseDto> findByPermission(Long idPermission);
}