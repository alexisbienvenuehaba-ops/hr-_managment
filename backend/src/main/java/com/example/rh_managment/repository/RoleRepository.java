package com.example.rh_managment.repository;

import com.example.rh_managment.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByNameRoleIgnoreCase(String nameRole);
    boolean existsByNameRoleIgnoreCaseAndIdRoleNot(String nameRole, Long idRole);
}