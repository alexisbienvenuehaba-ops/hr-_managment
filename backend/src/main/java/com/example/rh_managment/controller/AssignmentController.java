package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.AssignmentRequestDto;
import com.example.rh_managment.dto.response.AssignmentResponseDto;
import com.example.rh_managment.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentController {

    private final AssignmentService service;

    @PostMapping
    public ResponseEntity<AssignmentResponseDto> assign(@Valid @RequestBody AssignmentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.assign(dto));
    }

    @GetMapping("/{id}")
    public AssignmentResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/employee/{idEmployee}")
    public List<AssignmentResponseDto> findByEmployee(@PathVariable Long idEmployee) {
        return service.findByEmployee(idEmployee);
    }

    @GetMapping("/employee/{idEmployee}/current")
    public AssignmentResponseDto findCurrent(@PathVariable Long idEmployee) {
        return service.findCurrentByEmployee(idEmployee);
    }
}