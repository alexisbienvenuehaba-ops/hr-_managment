package com.example.rh_managment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "service_assignment_history")
public class ServiceAssignmentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service_assignment_history")
    private Long idServiceAssignmentHistory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_service", nullable = false)
    private ServiceEntity service;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_department", nullable = false)
    private DepartmentEntity department;

    @Column(name = "start_date_history", nullable = false)
    private LocalDate startDateHistory;

    @Column(name = "end_date_history")
    private LocalDate endDateHistory;
}