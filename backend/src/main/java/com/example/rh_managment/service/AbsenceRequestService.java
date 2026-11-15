package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.AbsenceCancelRequestDto;
import com.example.rh_managment.dto.request.AbsenceDecisionRequestDto;
import com.example.rh_managment.dto.request.AbsenceOpinionRequestDto;
import com.example.rh_managment.dto.request.AbsenceRequestRequestDto;
import com.example.rh_managment.dto.response.AbsenceRequestResponseDto;
import com.example.rh_managment.enums.AbsenceStatus;

import java.util.List;

public interface AbsenceRequestService {
    AbsenceRequestResponseDto create(AbsenceRequestRequestDto dto);
    AbsenceRequestResponseDto giveManagerOpinion(Long id, AbsenceOpinionRequestDto dto);
    AbsenceRequestResponseDto decide(Long id, AbsenceDecisionRequestDto dto);
    AbsenceRequestResponseDto cancel(Long id, AbsenceCancelRequestDto dto);
    AbsenceRequestResponseDto findById(Long id);
    List<AbsenceRequestResponseDto> findAll();
    List<AbsenceRequestResponseDto> findByEmployee(Long idEmployee);
    List<AbsenceRequestResponseDto> findByStatus(AbsenceStatus status);
}