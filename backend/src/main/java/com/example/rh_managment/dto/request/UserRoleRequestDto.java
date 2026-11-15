package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRoleRequestDto {

    @NotNull(message = "User is required")
    private Long idUser;

    @NotNull(message = "Role is required")
    private Long idRole;
}