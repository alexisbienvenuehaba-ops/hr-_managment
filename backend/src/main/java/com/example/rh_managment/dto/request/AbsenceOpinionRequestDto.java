package com.example.rh_managment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AbsenceOpinionRequestDto {

    @NotBlank(message = "Manager opinion is required")
    @Size(max = 500, message = "Opinion must not exceed 500 characters")
    private String managerOpinionAbsence;
}