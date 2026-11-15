package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.ServiceRequestDto;
import com.example.rh_managment.dto.response.ServiceResponseDto;
import com.example.rh_managment.service.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceController {

    private final ServiceService service;

    @PostMapping
    public ResponseEntity<ServiceResponseDto> create(@Valid @RequestBody ServiceRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ServiceResponseDto update(@PathVariable Long id, @Valid @RequestBody ServiceRequestDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public ServiceResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<ServiceResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/active")
    public List<ServiceResponseDto> findAllActive() {
        return service.findAllActive();
    }

    @GetMapping("/department/{idDepartment}")
    public List<ServiceResponseDto> findByDepartment(@PathVariable Long idDepartment) {
        return service.findByDepartment(idDepartment);
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