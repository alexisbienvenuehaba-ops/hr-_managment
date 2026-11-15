package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.EmployeeRequestDto;
import com.example.rh_managment.dto.response.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto create(EmployeeRequestDto dto);
    EmployeeResponseDto update(Long id, EmployeeRequestDto dto);
    EmployeeResponseDto findById(Long id);
    List<EmployeeResponseDto> findAll();
}