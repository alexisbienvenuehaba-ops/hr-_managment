package com.example.rh_managment.dto.response;

import lombok.Data;

@Data
public class UserRoleResponseDto {
    private Long idUser;
    private String usernameUser;
    private Long idRole;
    private String nameRole;
}