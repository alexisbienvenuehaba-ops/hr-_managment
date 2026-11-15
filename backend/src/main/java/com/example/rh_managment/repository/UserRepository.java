package com.example.rh_managment.repository;

import com.example.rh_managment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsernameUserIgnoreCase(String usernameUser);
    boolean existsByUsernameUserIgnoreCaseAndIdUserNot(String usernameUser, Long idUser);
    boolean existsByEmployee_IdEmployee(Long idEmployee);
}