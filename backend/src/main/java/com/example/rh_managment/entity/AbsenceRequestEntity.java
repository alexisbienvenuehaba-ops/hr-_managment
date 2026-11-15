package com.example.rh_managment.entity;

import com.example.rh_managment.enums.AbsenceStatus;
import com.example.rh_managment.enums.AbsenceType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "absence_request")
public class AbsenceRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_absence_request")
    private Long idAbsenceRequest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employee", nullable = false)
    private EmployeeEntity employee;

    // L'utilisateur RH qui a traité la demande (vide tant qu'elle est en attente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_handler_user")
    private UserEntity handlerUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_absence", nullable = false)
    private AbsenceType typeAbsence;

    @Column(name = "start_date_absence", nullable = false)
    private LocalDateTime startDateAbsence;

    @Column(name = "end_date_absence", nullable = false)
    private LocalDateTime endDateAbsence;

    @Column(name = "reason_absence")
    private String reasonAbsence;

    @Column(name = "supporting_document_absence")
    private String supportingDocumentAbsence;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_absence", nullable = false)
    private AbsenceStatus statusAbsence = AbsenceStatus.PENDING;

    @Column(name = "manager_opinion_absence")
    private String managerOpinionAbsence;

    @Column(name = "hr_decision_absence")
    private String hrDecisionAbsence;
}