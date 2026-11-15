package com.example.rh_managment.dto.response;

import com.example.rh_managment.enums.ContractStatus;
import com.example.rh_managment.enums.ContractType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContractResponseDto {
    private Long idContract;
    private Long idEmployee;
    private String registrationNumber;
    private String employeeFullName;
    private String referenceContract;
    private ContractType typeContract;
    private LocalDate startDateContract;
    private LocalDate endDateContract;
    private BigDecimal remunerationContract;
    private ContractStatus statusContract;
    private String documentContract;
}