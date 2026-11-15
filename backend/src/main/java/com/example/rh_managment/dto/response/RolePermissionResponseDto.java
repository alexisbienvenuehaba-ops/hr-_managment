package com.example.rh_managment.dto.response;

import lombok.Data;

@Data
public class RolePermissionResponseDto {
    private Long idRole;
    private String nameRole;
    private Long idPermission;
    private String codePermission;
    private String descriptionPermission;
}