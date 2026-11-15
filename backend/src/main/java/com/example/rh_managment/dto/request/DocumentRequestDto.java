package com.example.rh_managment.dto.request;

import com.example.rh_managment.enums.DocumentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentRequestDto {

    @NotNull(message = "Employee is required")
    private Long idEmployee;

    @NotBlank(message = "Document name is required")
    @Size(max = 150, message = "Document name must not exceed 150 characters")
    private String nameDocument;

    @NotNull(message = "Document type is required")
    private DocumentType typeDocument;

    @NotBlank(message = "File is required")
    @Size(max = 255, message = "File must not exceed 255 characters")
    private String fileDocument;

    @FutureOrPresent(message = "Expiration date cannot be in the past")
    private LocalDate expirationDateDocument;
}