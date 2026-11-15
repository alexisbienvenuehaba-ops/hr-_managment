package com.example.rh_managment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "action_history")
public class ActionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_action_history")
    private Long idActionHistory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;

    @Column(name = "action_history", nullable = false)
    private String actionHistory;

    @Column(name = "entity_name_history")
    private String entityNameHistory;

    @Column(name = "entity_id_history")
    private Long entityIdHistory;

    @Column(name = "old_value_history", columnDefinition = "TEXT")
    private String oldValueHistory;

    @Column(name = "new_value_history", columnDefinition = "TEXT")
    private String newValueHistory;

    @Column(name = "action_date_history", nullable = false, updatable = false)
    private LocalDateTime actionDateHistory;
}