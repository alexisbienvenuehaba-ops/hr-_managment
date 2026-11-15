package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.PositionRequestDto;
import com.example.rh_managment.dto.response.PositionResponseDto;
import com.example.rh_managment.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PositionController {

    private final PositionService service;

    @PostMapping
    public ResponseEntity<PositionResponseDto> create(@Valid @RequestBody PositionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public PositionResponseDto update(@PathVariable Long id, @Valid @RequestBody PositionRequestDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public PositionResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<PositionResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/active")
    public List<PositionResponseDto> findAllActive() {
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