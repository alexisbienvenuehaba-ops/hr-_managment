package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RolePermissionRequestDto {

    @NotNull(message = "Role is required")
    private Long idRole;

    @NotNull(message = "Permission is required")
    private Long idPermission;
}