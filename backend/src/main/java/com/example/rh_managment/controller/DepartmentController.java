package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.DepartmentRequestDto;
import com.example.rh_managment.dto.response.DepartmentResponseDto;
import com.example.rh_managment.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class  DepartmentController {

    private final DepartmentService service;

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> create(@Valid @RequestBody DepartmentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public DepartmentResponseDto update(@PathVariable Long id, @Valid @RequestBody DepartmentRequestDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public DepartmentResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<DepartmentResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/active")
    public List<DepartmentResponseDto> findAllActive() {
        return service.findAllActive();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        service.activate(id);
        return ResponseEntity.noContent().build();
    }
}