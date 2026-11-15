package com.example.rh_managment.repository;

import com.example.rh_managment.entity.AbsenceRequestEntity;
import com.example.rh_managment.enums.AbsenceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsenceRequestRepository extends JpaRepository<AbsenceRequestEntity, Long> {
    List<AbsenceRequestEntity> findAllByOrderByStartDateAbsenceDesc();
    List<AbsenceRequestEntity> findByEmployee_IdEmployeeOrderByStartDateAbsenceDesc(Long idEmployee);
    List<AbsenceRequestEntity> findByStatusAbsenceOrderByStartDateAbsenceDesc(AbsenceStatus statusAbsence);
}