package com.example.rh_managment.dto.response;

import com.example.rh_managment.enums.AbsenceStatus;
import com.example.rh_managment.enums.AbsenceType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AbsenceRequestResponseDto {
    private Long idAbsenceRequest;
    private Long idEmployee;
    private String employeeFullName;
    private Long idHandlerUser;
    private String handlerUsername;
    private AbsenceType typeAbsence;
    private LocalDateTime startDateAbsence;
    private LocalDateTime endDateAbsence;
    private String reasonAbsence;
    private String supportingDocumentAbsence;
    private AbsenceStatus statusAbsence;
    private String managerOpinionAbsence;
    private String hrDecisionAbsence;
}