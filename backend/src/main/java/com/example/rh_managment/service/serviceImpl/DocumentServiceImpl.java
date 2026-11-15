package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.DocumentRequestDto;
import com.example.rh_managment.dto.request.DocumentVersionRequestDto;
import com.example.rh_managment.dto.response.DocumentResponseDto;
import com.example.rh_managment.entity.DocumentEntity;
import com.example.rh_managment.entity.EmployeeEntity;
import com.example.rh_managment.mapper.DocumentMapper;
import com.example.rh_managment.repository.DocumentRepository;
import com.example.rh_managment.repository.EmployeeRepository;
import com.example.rh_managment.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final EmployeeRepository employeeRepository;
    private final DocumentMapper mapper;

    @Override
    public DocumentResponseDto create(DocumentRequestDto dto) {
        EmployeeEntity employee = employeeRepository.findById(dto.getIdEmployee()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        String name = dto.getNameDocument().trim();
        if (repository.existsByEmployee_IdEmployeeAndTypeDocumentAndNameDocumentIgnoreCase(
                employee.getIdEmployee(), dto.getTypeDocument(), name)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This document already exists for this employee: add a new version instead");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(dto, employee)));
    }

    @Override
    public DocumentResponseDto addVersion(Long id, DocumentVersionRequestDto dto) {
        DocumentEntity document = getOrThrow(id);
        if (Boolean.TRUE.equals(document.getArchivedDocument())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This document is archived");
        }
        DocumentEntity latest = family(document).get(0);
        if (!latest.getIdDocument().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Only the latest version can be replaced");
        }
        return mapper.toResponse(repository.save(mapper.toNewVersion(latest, dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentResponseDto> findVersions(Long id) {
        return family(getOrThrow(id)).stream().map(mapper::toResponse).toList();
    }

    // Documents non archivés d'un employé : seulement la dernière version de chacun
    @Override
    @Transactional(readOnly = true)
    public List<DocumentResponseDto> findByEmployee(Long idEmployee) {
        Map<String, DocumentEntity> latestByDocument = repository
                .findByEmployee_IdEmployeeAndArchivedDocumentFalse(idEmployee).stream()
                .collect(Collectors.toMap(
                        d -> d.getTypeDocument() + "|" + d.getNameDocument().toLowerCase(),
                        Function.identity(),
                        (a, b) -> a.getVersionDocument() >= b.getVersionDocument() ? a : b));

        return latestByDocument.values().stream()
                .sorted(Comparator.comparing(DocumentEntity::getIdDocument))
                .map(mapper::toResponse)
                .toList();
    }

    // Archivage logique : toutes les versions du document sont archivées ensemble
    @Override
    public void archive(Long id) {
        List<DocumentEntity> versions = family(getOrThrow(id));
        versions.forEach(d -> d.setArchivedDocument(true));
        repository.saveAll(versions);
    }

    private List<DocumentEntity> family(DocumentEntity d) {
        return repository
                .findByEmployee_IdEmployeeAndTypeDocumentAndNameDocumentIgnoreCaseOrderByVersionDocumentDesc(
                        d.getEmployee().getIdEmployee(), d.getTypeDocument(), d.getNameDocument());
    }

    private DocumentEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not found"));
    }
}