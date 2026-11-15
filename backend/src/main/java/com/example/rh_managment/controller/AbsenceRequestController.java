package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.AbsenceCancelRequestDto;
import com.example.rh_managment.dto.request.AbsenceDecisionRequestDto;
import com.example.rh_managment.dto.request.AbsenceOpinionRequestDto;
import com.example.rh_managment.dto.request.AbsenceRequestRequestDto;
import com.example.rh_managment.dto.response.AbsenceRequestResponseDto;
import com.example.rh_managment.enums.AbsenceStatus;
import com.example.rh_managment.service.AbsenceRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absence-requests")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AbsenceRequestController {

    private final AbsenceRequestService service;

    @PostMapping
    public ResponseEntity<AbsenceRequestResponseDto> create(@Valid @RequestBody AbsenceRequestRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PatchMapping("/{id}/manager-opinion")
    public AbsenceRequestResponseDto giveManagerOpinion(@PathVariable Long id,
                                                        @Valid @RequestBody AbsenceOpinionRequestDto dto) {
        return service.giveManagerOpinion(id, dto);
    }

    @PatchMapping("/{id}/decision")
    public AbsenceRequestResponseDto decide(@PathVariable Long id,
                                            @Valid @RequestBody AbsenceDecisionRequestDto dto) {
        return service.decide(id, dto);
    }

    @PatchMapping("/{id}/cancel")
    public AbsenceRequestResponseDto cancel(@PathVariable Long id,
                                            @RequestBody(required = false) AbsenceCancelRequestDto dto) {
        return service.cancel(id, dto);
    }

    @GetMapping("/{id}")
    public AbsenceRequestResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<AbsenceRequestResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/employee/{idEmployee}")
    public List<AbsenceRequestResponseDto> findByEmployee(@PathVariable Long idEmployee) {
        return service.findByEmployee(idEmployee);
    }

    @GetMapping("/status/{status}")
    public List<AbsenceRequestResponseDto> findByStatus(@PathVariable AbsenceStatus status) {
        return service.findByStatus(status);
    }
}