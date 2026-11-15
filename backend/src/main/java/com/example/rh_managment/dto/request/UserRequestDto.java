package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {

    // Optionnel : vide pour un compte d'administration
    private Long idEmployee;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must contain between 3 and 50 characters")
    @Pattern(regexp = "^[A-Za-z0-9._-]+$",
            message = "Username may only contain letters, digits, dots, hyphens and underscores")
    private String usernameUser;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must contain between 8 and 100 characters")
    private String passwordUser;
}