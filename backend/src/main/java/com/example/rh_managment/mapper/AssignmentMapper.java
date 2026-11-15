package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.AssignmentRequestDto;
import com.example.rh_managment.dto.response.AssignmentResponseDto;
import com.example.rh_managment.entity.AssignmentEntity;
import com.example.rh_managment.entity.EmployeeEntity;
import com.example.rh_managment.entity.PositionEntity;
import com.example.rh_managment.entity.ServiceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AssignmentMapper {

    // Le département est celui du service au moment de l'affectation
    public AssignmentEntity toEntity(AssignmentRequestDto dto, EmployeeEntity employee,
                                     PositionEntity position, ServiceEntity service) {
        AssignmentEntity e = new AssignmentEntity();
        e.setEmployee(employee);
        e.setPosition(position);
        e.setService(service);
        e.setDepartment(service.getDepartment());
        e.setStartDateAssignment(LocalDate.now());
        e.setReasonAssignment(
                dto.getReasonAssignment() == null || dto.getReasonAssignment().isBlank()
                        ? null : dto.getReasonAssignment().trim());
        return e;
    }

    public AssignmentResponseDto toResponse(AssignmentEntity e) {
        AssignmentResponseDto r = new AssignmentResponseDto();
        r.setIdAssignment(e.getIdAssignment());
        r.setIdEmployee(e.getEmployee().getIdEmployee());
        r.setRegistrationNumber(e.getEmployee().getRegistrationNumber());
        r.setEmployeeFullName(e.getEmployee().getFirstName() + " " + e.getEmployee().getLastName());
        r.setIdPosition(e.getPosition().getIdPosition());
        r.setPositionName(e.getPosition().getNamePosition());
        r.setIdService(e.getService().getIdService());
        r.setNameService(e.getService().getNameService());
        r.setIdDepartment(e.getDepartment().getIdDepartment());
        r.setDepartmentName(e.getDepartment().getName());
        r.setStartDateAssignment(e.getStartDateAssignment());
        r.setEndDateAssignment(e.getEndDateAssignment());
        r.setReasonAssignment(e.getReasonAssignment());
        return r;
    }
}