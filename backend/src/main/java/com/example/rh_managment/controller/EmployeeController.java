package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.EmployeeRequestDto;
import com.example.rh_managment.dto.response.EmployeeResponseDto;
import com.example.rh_managment.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> create(@Valid @RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto update(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<EmployeeResponseDto> findAll() {
        return service.findAll();
    }
}