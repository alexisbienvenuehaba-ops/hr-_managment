package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.ServiceRequestDto;
import com.example.rh_managment.dto.response.ServiceResponseDto;

import java.util.List;

public interface ServiceService {
    ServiceResponseDto create(ServiceRequestDto dto);
    ServiceResponseDto update(Long id, ServiceRequestDto dto);
    ServiceResponseDto findById(Long id);
    List<ServiceResponseDto> findAll();
    List<ServiceResponseDto> findAllActive();
    List<ServiceResponseDto> findByDepartment(Long idDepartment);
    void deactivate(Long id);
    void activate(Long id);
}