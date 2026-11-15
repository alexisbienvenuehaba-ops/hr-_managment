package com.example.rh_managment.repository;

import com.example.rh_managment.entity.RolePermissionEntity;
import com.example.rh_managment.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, RolePermissionId> {
    List<RolePermissionEntity> findByRole_IdRole(Long idRole);
    List<RolePermissionEntity> findByPermission_IdPermission(Long idPermission);
}