package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.PositionRequestDto;
import com.example.rh_managment.dto.response.PositionResponseDto;

import java.util.List;

public interface PositionService {
    PositionResponseDto create(PositionRequestDto dto);
    PositionResponseDto update(Long id, PositionRequestDto dto);
    PositionResponseDto findById(Long id);
    List<PositionResponseDto> findAll();
    List<PositionResponseDto> findAllActive();
    void deactivate(Long id);
    void activate(Long id);
}