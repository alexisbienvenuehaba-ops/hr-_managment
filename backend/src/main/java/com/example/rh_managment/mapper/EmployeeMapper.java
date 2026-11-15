package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.EmployeeRequestDto;
import com.example.rh_managment.dto.response.EmployeeResponseDto;
import com.example.rh_managment.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeEntity toEntity(EmployeeRequestDto dto) {
        EmployeeEntity e = new EmployeeEntity();
        updateEntity(e, dto);
        return e;
    }

    public void updateEntity(EmployeeEntity e, EmployeeRequestDto dto) {
        e.setLastName(dto.getLastName().trim());
        e.setFirstName(dto.getFirstName().trim());
        e.setBirthDate(dto.getBirthDate());
        e.setGender(dto.getGender());
        e.setPhone(clean(dto.getPhone()));
        e.setEmail(clean(dto.getEmail()));
        e.setAddress(clean(dto.getAddress()));
        e.setPhoto(clean(dto.getPhoto()));
        e.setHireDate(dto.getHireDate());
    }

    public EmployeeResponseDto toResponse(EmployeeEntity e) {
        EmployeeResponseDto r = new EmployeeResponseDto();
        r.setIdEmployee(e.getIdEmployee());
        r.setRegistrationNumber(e.getRegistrationNumber());
        r.setLastName(e.getLastName());
        r.setFirstName(e.getFirstName());
        r.setBirthDate(e.getBirthDate());
        r.setGender(e.getGender());
        r.setPhone(e.getPhone());
        r.setEmail(e.getEmail());
        r.setAddress(e.getAddress());
        r.setPhoto(e.getPhoto());
        r.setHireDate(e.getHireDate());
        r.setEmployeeStatus(e.getEmployeeStatus());
        r.setExitDate(e.getExitDate());
        r.setExitReason(e.getExitReason());
        return r;
    }

    // Transforme les chaînes vides en null (important pour l'e-mail unique)
    private String clean(String value) {
        if (value == null) return null;
        String v = value.trim();
        return v.isEmpty() ? null : v;
    }
}