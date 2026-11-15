package com.example.rh_managment.dto.response;

import com.example.rh_managment.enums.DepartmentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentResponseDto {
    private Long idDepartment;
    private String name;
    private String description;
    private DepartmentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}