package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.PositionRequestDto;
import com.example.rh_managment.dto.response.PositionResponseDto;
import com.example.rh_managment.entity.PositionEntity;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {

    public PositionEntity toEntity(PositionRequestDto dto) {
        PositionEntity e = new PositionEntity();
        updateEntity(e, dto);
        return e;
    }

    public void updateEntity(PositionEntity e, PositionRequestDto dto) {
        e.setNamePosition(dto.getNamePosition().trim());
        e.setDescriptionPosition(dto.getDescriptionPosition() == null ? null : dto.getDescriptionPosition().trim());
    }

    public PositionResponseDto toResponse(PositionEntity e) {
        PositionResponseDto r = new PositionResponseDto();
        r.setIdPosition(e.getIdPosition());
        r.setNamePosition(e.getNamePosition());
        r.setDescriptionPosition(e.getDescriptionPosition());
        r.setStatus(e.getStatus());
        return r;
    }
}