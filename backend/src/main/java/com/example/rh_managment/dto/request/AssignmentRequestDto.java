package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AssignmentRequestDto {

    @NotNull(message = "Employee is required")
    private Long idEmployee;

    @NotNull(message = "Position is required")
    private Long idPosition;

    @NotNull(message = "Service is required")
    private Long idService;

    @Size(max = 500, message = "Reason must not exceed 500 characters")
    private String reasonAssignment;
}