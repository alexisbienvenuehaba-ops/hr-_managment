package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.ContractRequestDto;
import com.example.rh_managment.dto.response.ContractResponseDto;

import java.util.List;

public interface ContractService {
    ContractResponseDto create(ContractRequestDto dto);
    ContractResponseDto update(Long id, ContractRequestDto dto);
    ContractResponseDto findById(Long id);
    List<ContractResponseDto> findAll();
    List<ContractResponseDto> findByEmployee(Long idEmployee);
    ContractResponseDto terminate(Long id);
}