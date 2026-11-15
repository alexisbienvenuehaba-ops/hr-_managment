package com.example.rh_managment.repository;

import com.example.rh_managment.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {

    List<AssignmentEntity> findByEmployee_IdEmployeeOrderByStartDateAssignmentDesc(Long idEmployee);

    Optional<AssignmentEntity> findByEmployee_IdEmployeeAndEndDateAssignmentIsNull(Long idEmployee);
}