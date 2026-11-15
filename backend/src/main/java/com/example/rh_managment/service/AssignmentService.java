package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.AssignmentRequestDto;
import com.example.rh_managment.dto.response.AssignmentResponseDto;

import java.util.List;

public interface AssignmentService {
    AssignmentResponseDto assign(AssignmentRequestDto dto);
    AssignmentResponseDto findById(Long id);
    List<AssignmentResponseDto> findByEmployee(Long idEmployee);
    AssignmentResponseDto findCurrentByEmployee(Long idEmployee);
}