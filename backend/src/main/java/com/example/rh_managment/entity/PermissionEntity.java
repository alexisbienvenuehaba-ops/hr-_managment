package com.example.rh_managment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "permission")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission")
    private Long idPermission;

    @Column(name = "code_permission", nullable = false, unique = true)
    private String codePermission;

    @Column(name = "description_permission")
    private String descriptionPermission;
}