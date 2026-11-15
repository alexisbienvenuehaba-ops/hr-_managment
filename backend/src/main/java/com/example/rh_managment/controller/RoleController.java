package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.RoleRequestDto;
import com.example.rh_managment.dto.response.RoleResponseDto;
import com.example.rh_managment.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    private final RoleService service;

    @PostMapping
    public ResponseEntity<RoleResponseDto> create(@Valid @RequestBody RoleRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public RoleResponseDto update(@PathVariable Long id, @Valid @RequestBody RoleRequestDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public RoleResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<RoleResponseDto> findAll() {
        return service.findAll();
    }
}