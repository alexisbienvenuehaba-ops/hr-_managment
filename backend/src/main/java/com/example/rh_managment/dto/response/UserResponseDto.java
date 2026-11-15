package com.example.rh_managment.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long idUser;
    private Long idEmployee;
    private String employeeFullName;
    private String usernameUser;
    private Boolean activeUser;
    private Boolean passwordChangeRequiredUser;
}