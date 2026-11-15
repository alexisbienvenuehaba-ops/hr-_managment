package com.example.rh_managment.dto.response;

import com.example.rh_managment.enums.EmployeeStatus;
import com.example.rh_managment.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeResponseDto {
    private Long idEmployee;
    private String registrationNumber;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private Gender gender;
    private String phone;
    private String email;
    private String address;
    private String photo;
    private LocalDate hireDate;
    private EmployeeStatus employeeStatus;
    private LocalDate exitDate;
    private String exitReason;
}