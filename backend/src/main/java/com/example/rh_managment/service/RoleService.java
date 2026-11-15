package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.RoleRequestDto;
import com.example.rh_managment.dto.response.RoleResponseDto;

import java.util.List;

public interface RoleService {
    RoleResponseDto create(RoleRequestDto dto);
    RoleResponseDto update(Long id, RoleRequestDto dto);
    RoleResponseDto findById(Long id);
    List<RoleResponseDto> findAll();
}