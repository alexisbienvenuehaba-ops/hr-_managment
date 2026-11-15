package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPasswordRequestDto {

    // Obligatoire pour changer son mot de passe, ignoré pour une réinitialisation
    private String oldPasswordUser;

    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 100, message = "Password must contain between 8 and 100 characters")
    private String newPasswordUser;
}