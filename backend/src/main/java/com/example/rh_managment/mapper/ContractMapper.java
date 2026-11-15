package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.ContractRequestDto;
import com.example.rh_managment.dto.response.ContractResponseDto;
import com.example.rh_managment.entity.ContractEntity;
import com.example.rh_managment.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {

    public ContractEntity toEntity(ContractRequestDto dto, EmployeeEntity employee) {
        ContractEntity e = new ContractEntity();
        e.setEmployee(employee);
        updateEntity(e, dto);
        return e;
    }

    public void updateEntity(ContractEntity e, ContractRequestDto dto) {
        e.setReferenceContract(dto.getReferenceContract().trim());
        e.setTypeContract(dto.getTypeContract());
        e.setStartDateContract(dto.getStartDateContract());
        e.setEndDateContract(dto.getEndDateContract());
        e.setRemunerationContract(dto.getRemunerationContract());
        e.setDocumentContract(clean(dto.getDocumentContract()));
    }

    public ContractResponseDto toResponse(ContractEntity e) {
        ContractResponseDto r = new ContractResponseDto();
        r.setIdContract(e.getIdContract());
        r.setIdEmployee(e.getEmployee().getIdEmployee());
        r.setRegistrationNumber(e.getEmployee().getRegistrationNumber());
        r.setEmployeeFullName(e.getEmployee().getFirstName() + " " + e.getEmployee().getLastName());
        r.setReferenceContract(e.getReferenceContract());
        r.setTypeContract(e.getTypeContract());
        r.setStartDateContract(e.getStartDateContract());
        r.setEndDateContract(e.getEndDateContract());
        r.setRemunerationContract(e.getRemunerationContract());
        r.setStatusContract(e.getStatusContract());
        r.setDocumentContract(e.getDocumentContract());
        return r;
    }

    private String clean(String value) {
        if (value == null) return null;
        String v = value.trim();
        return v.isEmpty() ? null : v;
    }
}