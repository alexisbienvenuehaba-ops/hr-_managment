package com.example.rh_managment.entity;

import com.example.rh_managment.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "document")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private Long idDocument;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employee", nullable = false)
    private EmployeeEntity employee;

    @Column(name = "name_document", nullable = false)
    private String nameDocument;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_document", nullable = false)
    private DocumentType typeDocument;

    @Column(name = "file_document", nullable = false)
    private String fileDocument;

    @Column(name = "expiration_date_document")
    private LocalDate expirationDateDocument;

    @Column(name = "version_document", nullable = false)
    private Integer versionDocument = 1;

    @Column(name = "archived_document", nullable = false)
    private Boolean archivedDocument = false;
}