package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.DepartmentRequestDto;
import com.example.rh_managment.dto.response.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDto create(DepartmentRequestDto dto);
    DepartmentResponseDto update(Long id, DepartmentRequestDto dto);
    DepartmentResponseDto findById(Long id);
    List<DepartmentResponseDto> findAll();
    List<DepartmentResponseDto> findAllActive();
    void deactivate(Long id);
    void activate(Long id);
}