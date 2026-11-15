package com.example.rh_managment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "name_role", nullable = false, unique = true)
    private String nameRole;

    @Column(name = "description_role")
    private String descriptionRole;
}