package com.example.rh_managment.dto.request;

import com.example.rh_managment.enums.ContractType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContractRequestDto {

    @NotNull(message = "Employee is required")
    private Long idEmployee;

    @NotBlank(message = "Contract reference is required")
    @Size(max = 100, message = "Reference must not exceed 100 characters")
    private String referenceContract;

    @NotNull(message = "Contract type is required")
    private ContractType typeContract;

    @NotNull(message = "Start date is required")
    private LocalDate startDateContract;

    private LocalDate endDateContract;

    @PositiveOrZero(message = "Remuneration cannot be negative")
    private BigDecimal remunerationContract;

    @Size(max = 255, message = "Document must not exceed 255 characters")
    private String documentContract;
}