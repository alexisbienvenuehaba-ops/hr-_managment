package com.example.rh_managment.repository;

import com.example.rh_managment.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    boolean existsByNamePositionIgnoreCase(String name);
    boolean existsByNamePositionIgnoreCaseAndIdPositionNot(String name, Long idPosition);
}