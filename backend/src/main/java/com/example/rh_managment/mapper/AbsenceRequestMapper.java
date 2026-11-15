package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.AbsenceRequestRequestDto;
import com.example.rh_managment.dto.response.AbsenceRequestResponseDto;
import com.example.rh_managment.entity.AbsenceRequestEntity;
import com.example.rh_managment.entity.EmployeeEntity;
import com.example.rh_managment.enums.AbsenceStatus;
import org.springframework.stereotype.Component;

@Component
public class AbsenceRequestMapper {

    public AbsenceRequestEntity toEntity(AbsenceRequestRequestDto dto, EmployeeEntity employee) {
        AbsenceRequestEntity e = new AbsenceRequestEntity();
        e.setEmployee(employee);
        e.setTypeAbsence(dto.getTypeAbsence());
        e.setStartDateAbsence(dto.getStartDateAbsence());
        e.setEndDateAbsence(dto.getEndDateAbsence());
        e.setReasonAbsence(clean(dto.getReasonAbsence()));
        e.setSupportingDocumentAbsence(clean(dto.getSupportingDocumentAbsence()));
        e.setStatusAbsence(AbsenceStatus.PENDING);
        return e;
    }

    public AbsenceRequestResponseDto toResponse(AbsenceRequestEntity e) {
        AbsenceRequestResponseDto r = new AbsenceRequestResponseDto();
        r.setIdAbsenceRequest(e.getIdAbsenceRequest());
        r.setIdEmployee(e.getEmployee().getIdEmployee());
        r.setEmployeeFullName(e.getEmployee().getFirstName() + " " + e.getEmployee().getLastName());
        if (e.getHandlerUser() != null) {
            r.setIdHandlerUser(e.getHandlerUser().getIdUser());
            r.setHandlerUsername(e.getHandlerUser().getUsernameUser());
        }
        r.setTypeAbsence(e.getTypeAbsence());
        r.setStartDateAbsence(e.getStartDateAbsence());
        r.setEndDateAbsence(e.getEndDateAbsence());
        r.setReasonAbsence(e.getReasonAbsence());
        r.setSupportingDocumentAbsence(e.getSupportingDocumentAbsence());
        r.setStatusAbsence(e.getStatusAbsence());
        r.setManagerOpinionAbsence(e.getManagerOpinionAbsence());
        r.setHrDecisionAbsence(e.getHrDecisionAbsence());
        return r;
    }

    private String clean(String value) {
        if (value == null) return null;
        String v = value.trim();
        return v.isEmpty() ? null : v;
    }
}