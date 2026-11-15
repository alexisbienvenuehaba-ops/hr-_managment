package com.example.rh_managment.dto.response;

import com.example.rh_managment.enums.DocumentType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentResponseDto {
    private Long idDocument;
    private Long idEmployee;
    private String employeeFullName;
    private String nameDocument;
    private DocumentType typeDocument;
    private String fileDocument;
    private LocalDate expirationDateDocument;
    private Integer versionDocument;
    private Boolean archivedDocument;
}