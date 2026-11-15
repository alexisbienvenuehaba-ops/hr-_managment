package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.PermissionRequestDto;
import com.example.rh_managment.dto.response.PermissionResponseDto;

import java.util.List;

public interface PermissionService {
    PermissionResponseDto create(PermissionRequestDto dto);
    PermissionResponseDto update(Long id, PermissionRequestDto dto);
    PermissionResponseDto findById(Long id);
    List<PermissionResponseDto> findAll();
}