package com.example.rh_managment.repository;

import com.example.rh_managment.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    boolean existsByCodePermissionIgnoreCase(String codePermission);
    boolean existsByCodePermissionIgnoreCaseAndIdPermissionNot(String codePermission, Long idPermission);
}