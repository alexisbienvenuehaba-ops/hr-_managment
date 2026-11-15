package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ServiceRequestDto {

    @NotNull(message = "Department is required")
    private Long idDepartment;

    @NotBlank(message = "Service name is required")
    @Size(max = 150, message = "Service name must not exceed 150 characters")
    private String nameService;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String descriptionService;
}