package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.ActionHistoryRequestDto;
import com.example.rh_managment.dto.response.ActionHistoryResponseDto;

import java.util.List;

public interface ActionHistoryService {
    // Sera appelée par les autres services pour écrire dans le journal
    ActionHistoryResponseDto record(ActionHistoryRequestDto dto);

    List<ActionHistoryResponseDto> findAll();
    List<ActionHistoryResponseDto> findByUser(Long idUser);
    List<ActionHistoryResponseDto> findByEntity(String entityName, Long entityId);
}