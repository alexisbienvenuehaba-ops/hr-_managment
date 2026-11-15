package com.example.rh_managment.dto.response;

import com.example.rh_managment.enums.ServiceStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServiceResponseDto {
    private Long idService;
    private Long idDepartment;
    private String departmentName;
    private String nameService;
    private String descriptionService;
    private ServiceStatus statusService;
    private LocalDateTime createdAtService;
    private LocalDateTime updatedAtService;
}