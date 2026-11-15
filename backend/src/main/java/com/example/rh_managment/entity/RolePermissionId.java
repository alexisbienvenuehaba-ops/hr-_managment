package com.example.rh_managment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RolePermissionId implements Serializable {

    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "id_permission")
    private Long idPermission;
}