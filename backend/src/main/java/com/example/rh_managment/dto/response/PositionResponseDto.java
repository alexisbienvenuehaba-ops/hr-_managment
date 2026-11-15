package com.example.rh_managment.dto.response;

import com.example.rh_managment.enums.PositionStatus;
import lombok.Data;

@Data
public class PositionResponseDto {
    private Long idPosition;
    private String namePosition;
    private String descriptionPosition;
    private PositionStatus status;
}