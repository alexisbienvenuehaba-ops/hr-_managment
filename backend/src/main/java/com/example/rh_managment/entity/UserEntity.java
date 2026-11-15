package com.example.rh_managment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    // Vide pour un compte d'administration sans dossier employé
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", unique = true)
    private EmployeeEntity employee;

    @Column(name = "username_user", nullable = false, unique = true)
    private String usernameUser;

    @Column(name = "password_hash_user", nullable = false)
    private String passwordHashUser;

    @Column(name = "active_user", nullable = false)
    private Boolean activeUser = true;

    @Column(name = "password_change_required_user", nullable = false)
    private Boolean passwordChangeRequiredUser = true;
}