package com.example.rh_managment.repository;

import com.example.rh_managment.entity.UserRoleEntity;
import com.example.rh_managment.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleId> {
    List<UserRoleEntity> findByUser_IdUser(Long idUser);
    List<UserRoleEntity> findByRole_IdRole(Long idRole);
}