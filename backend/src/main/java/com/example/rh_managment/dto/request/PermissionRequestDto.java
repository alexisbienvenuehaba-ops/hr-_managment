package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PermissionRequestDto {

    @NotBlank(message = "Permission code is required")
    @Size(max = 100, message = "Permission code must not exceed 100 characters")
    @Pattern(regexp = "^[A-Za-z0-9_]+$",
            message = "Permission code may only contain letters, digits and underscores")
    private String codePermission;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String descriptionPermission;
}