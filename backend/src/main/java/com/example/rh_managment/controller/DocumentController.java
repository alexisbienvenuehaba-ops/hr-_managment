package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.DocumentRequestDto;
import com.example.rh_managment.dto.request.DocumentVersionRequestDto;
import com.example.rh_managment.dto.response.DocumentResponseDto;
import com.example.rh_managment.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {

    private final DocumentService service;

    @PostMapping
    public ResponseEntity<DocumentResponseDto> create(@Valid @RequestBody DocumentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PostMapping("/{id}/versions")
    public ResponseEntity<DocumentResponseDto> addVersion(@PathVariable Long id,
                                                          @Valid @RequestBody DocumentVersionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addVersion(id, dto));
    }

    @GetMapping("/{id}")
    public DocumentResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/versions")
    public List<DocumentResponseDto> findVersions(@PathVariable Long id) {
        return service.findVersions(id);
    }

    @GetMapping("/employee/{idEmployee}")
    public List<DocumentResponseDto> findByEmployee(@PathVariable Long idEmployee) {
        return service.findByEmployee(idEmployee);
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<Void> archive(@PathVariable Long id) {
        service.archive(id);
        return ResponseEntity.noContent().build();
    }
}