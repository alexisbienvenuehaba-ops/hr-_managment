package com.example.rh_managment.dto.request;

import com.example.rh_managment.enums.AbsenceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AbsenceRequestRequestDto {

    @NotNull(message = "Employee is required")
    private Long idEmployee;

    @NotNull(message = "Absence type is required")
    private AbsenceType typeAbsence;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDateAbsence;

    @NotNull(message = "End date is required")
    private LocalDateTime endDateAbsence;

    @Size(max = 500, message = "Reason must not exceed 500 characters")
    private String reasonAbsence;

    @Size(max = 255, message = "Supporting document must not exceed 255 characters")
    private String supportingDocumentAbsence;
}