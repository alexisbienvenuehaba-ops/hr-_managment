package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PositionRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 150, message = "Name must not exceed 150 characters")
    private String namePosition;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String descriptionPosition;
}