package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequestDto {

    @NotBlank(message = "Role name is required")
    @Size(max = 100, message = "Role name must not exceed 100 characters")
    private String nameRole;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String descriptionRole;
}