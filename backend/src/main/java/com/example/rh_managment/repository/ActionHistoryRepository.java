package com.example.rh_managment.repository;

import com.example.rh_managment.entity.ActionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionHistoryRepository extends JpaRepository<ActionHistoryEntity, Long> {

    List<ActionHistoryEntity> findAllByOrderByActionDateHistoryDesc();

    List<ActionHistoryEntity> findByUser_IdUserOrderByActionDateHistoryDesc(Long idUser);

    List<ActionHistoryEntity> findByEntityNameHistoryIgnoreCaseAndEntityIdHistoryOrderByActionDateHistoryDesc(
            String entityNameHistory, Long entityIdHistory);
}