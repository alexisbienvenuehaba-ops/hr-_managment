package com.example.rh_managment.repository;

import com.example.rh_managment.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    boolean existsByNameServiceIgnoreCase(String nameService);
    boolean existsByNameServiceIgnoreCaseAndIdServiceNot(String nameService, Long idService);
    List<ServiceEntity> findByDepartment_IdDepartment(Long idDepartment);
}