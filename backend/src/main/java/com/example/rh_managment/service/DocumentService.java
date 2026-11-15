package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.DocumentRequestDto;
import com.example.rh_managment.dto.request.DocumentVersionRequestDto;
import com.example.rh_managment.dto.response.DocumentResponseDto;

import java.util.List;

public interface DocumentService {
    DocumentResponseDto create(DocumentRequestDto dto);
    DocumentResponseDto addVersion(Long id, DocumentVersionRequestDto dto);
    DocumentResponseDto findById(Long id);
    List<DocumentResponseDto> findVersions(Long id);
    List<DocumentResponseDto> findByEmployee(Long idEmployee);
    void archive(Long id);
}