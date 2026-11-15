package com.example.rh_managment.dto.request;

import com.example.rh_managment.enums.AbsenceStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AbsenceDecisionRequestDto {

    // L'utilisateur RH qui décide
    @NotNull(message = "User is required")
    private Long idUser;

    // APPROVED ou REJECTED
    @NotNull(message = "Decision is required")
    private AbsenceStatus decisionAbsence;

    // Obligatoire en cas de refus
    @Size(max = 500, message = "Decision comment must not exceed 500 characters")
    private String hrDecisionAbsence;
}