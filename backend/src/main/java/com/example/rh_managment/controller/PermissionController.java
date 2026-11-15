package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.PermissionRequestDto;
import com.example.rh_managment.dto.response.PermissionResponseDto;
import com.example.rh_managment.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PermissionController {

    private final PermissionService service;

    @PostMapping
    public ResponseEntity<PermissionResponseDto> create(@Valid @RequestBody PermissionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public PermissionResponseDto update(@PathVariable Long id, @Valid @RequestBody PermissionRequestDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public PermissionResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<PermissionResponseDto> findAll() {
        return service.findAll();
    }
}