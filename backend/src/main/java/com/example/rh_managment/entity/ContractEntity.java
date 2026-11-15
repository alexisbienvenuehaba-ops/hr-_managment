package com.example.rh_managment.entity;

import com.example.rh_managment.enums.ContractStatus;
import com.example.rh_managment.enums.ContractType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contract")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract")
    private Long idContract;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employee", nullable = false)
    private EmployeeEntity employee;

    @Column(name = "reference_contract", nullable = false, unique = true)
    private String referenceContract;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_contract", nullable = false)
    private ContractType typeContract;

    @Column(name = "start_date_contract", nullable = false)
    private LocalDate startDateContract;

    @Column(name = "end_date_contract")
    private LocalDate endDateContract;

    @Column(name = "remuneration_contract", precision = 15, scale = 2)
    private BigDecimal remunerationContract;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_contract", nullable = false)
    private ContractStatus statusContract = ContractStatus.ACTIVE;

    @Column(name = "document_contract")
    private String documentContract;
}