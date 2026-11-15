package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActionHistoryRequestDto {

    @NotNull(message = "User is required")
    private Long idUser;

    @NotBlank(message = "Action is required")
    @Size(max = 255, message = "Action must not exceed 255 characters")
    private String actionHistory;

    @Size(max = 100, message = "Entity name must not exceed 100 characters")
    private String entityNameHistory;

    private Long entityIdHistory;

    private String oldValueHistory;

    private String newValueHistory;
}