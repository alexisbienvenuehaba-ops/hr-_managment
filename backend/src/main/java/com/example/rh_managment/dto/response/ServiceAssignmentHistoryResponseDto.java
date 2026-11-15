package com.example.rh_managment.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ServiceAssignmentHistoryResponseDto {
    private Long idServiceAssignmentHistory;
    private Long idService;
    private String nameService;
    private Long idDepartment;
    private String departmentName;
    private LocalDate startDateHistory;
    private LocalDate endDateHistory;
}