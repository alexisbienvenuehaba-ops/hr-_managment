package com.example.rh_managment.repository;

import com.example.rh_managment.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdEmployeeNot(String email, Long idEmployee);
    long countByRegistrationNumberStartingWith(String prefix);
}