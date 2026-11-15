package com.example.rh_managment.repository;

import com.example.rh_managment.entity.DocumentEntity;
import com.example.rh_managment.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    boolean existsByEmployee_IdEmployeeAndTypeDocumentAndNameDocumentIgnoreCase(
            Long idEmployee, DocumentType typeDocument, String nameDocument);

    // Toutes les versions d'un même document, de la plus récente à la plus ancienne
    List<DocumentEntity> findByEmployee_IdEmployeeAndTypeDocumentAndNameDocumentIgnoreCaseOrderByVersionDocumentDesc(
            Long idEmployee, DocumentType typeDocument, String nameDocument);

    List<DocumentEntity> findByEmployee_IdEmployeeAndArchivedDocumentFalse(Long idEmployee);
}