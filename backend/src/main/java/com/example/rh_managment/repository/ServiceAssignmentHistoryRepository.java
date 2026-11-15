package com.example.rh_managment.repository;

import com.example.rh_managment.entity.ServiceAssignmentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceAssignmentHistoryRepository
        extends JpaRepository<ServiceAssignmentHistoryEntity, Long> {

    List<ServiceAssignmentHistoryEntity> findByService_IdServiceOrderByStartDateHistoryDesc(Long idService);

    Optional<ServiceAssignmentHistoryEntity> findByService_IdServiceAndEndDateHistoryIsNull(Long idService);
}