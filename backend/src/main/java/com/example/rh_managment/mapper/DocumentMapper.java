package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.DocumentRequestDto;
import com.example.rh_managment.dto.request.DocumentVersionRequestDto;
import com.example.rh_managment.dto.response.DocumentResponseDto;
import com.example.rh_managment.entity.DocumentEntity;
import com.example.rh_managment.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentEntity toEntity(DocumentRequestDto dto, EmployeeEntity employee) {
        DocumentEntity e = new DocumentEntity();
        e.setEmployee(employee);
        e.setNameDocument(dto.getNameDocument().trim());
        e.setTypeDocument(dto.getTypeDocument());
        e.setFileDocument(dto.getFileDocument().trim());
        e.setExpirationDateDocument(dto.getExpirationDateDocument());
        e.setVersionDocument(1);
        e.setArchivedDocument(false);
        return e;
    }

    // Nouvelle version : mêmes employé, nom et type, version + 1
    public DocumentEntity toNewVersion(DocumentEntity previous, DocumentVersionRequestDto dto) {
        DocumentEntity e = new DocumentEntity();
        e.setEmployee(previous.getEmployee());
        e.setNameDocument(previous.getNameDocument());
        e.setTypeDocument(previous.getTypeDocument());
        e.setFileDocument(dto.getFileDocument().trim());
        e.setExpirationDateDocument(dto.getExpirationDateDocument());
        e.setVersionDocument(previous.getVersionDocument() + 1);
        e.setArchivedDocument(false);
        return e;
    }

    public DocumentResponseDto toResponse(DocumentEntity e) {
        DocumentResponseDto r = new DocumentResponseDto();
        r.setIdDocument(e.getIdDocument());
        r.setIdEmployee(e.getEmployee().getIdEmployee());
        r.setEmployeeFullName(e.getEmployee().getFirstName() + " " + e.getEmployee().getLastName());
        r.setNameDocument(e.getNameDocument());
        r.setTypeDocument(e.getTypeDocument());
        r.setFileDocument(e.getFileDocument());
        r.setExpirationDateDocument(e.getExpirationDateDocument());
        r.setVersionDocument(e.getVersionDocument());
        r.setArchivedDocument(e.getArchivedDocument());
        return r;
    }
}