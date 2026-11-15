package com.example.rh_managment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role_permission")
public class RolePermissionEntity {

    @EmbeddedId
    private RolePermissionId id = new RolePermissionId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idRole")
    @JoinColumn(name = "id_role")
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPermission")
    @JoinColumn(name = "id_permission")
    private PermissionEntity permission;
}