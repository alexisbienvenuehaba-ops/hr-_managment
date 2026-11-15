package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentVersionRequestDto {

    @NotBlank(message = "File is required")
    @Size(max = 255, message = "File must not exceed 255 characters")
    private String fileDocument;

    @FutureOrPresent(message = "Expiration date cannot be in the past")
    private LocalDate expirationDateDocument;
}