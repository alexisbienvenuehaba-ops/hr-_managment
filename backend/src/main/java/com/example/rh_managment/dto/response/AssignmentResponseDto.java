package com.example.rh_managment.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AssignmentResponseDto {
    private Long idAssignment;
    private Long idEmployee;
    private String registrationNumber;
    private String employeeFullName;
    private Long idPosition;
    private String positionName;
    private Long idService;
    private String nameService;
    private Long idDepartment;
    private String departmentName;
    private LocalDate startDateAssignment;
    private LocalDate endDateAssignment;
    private String reasonAssignment;
}