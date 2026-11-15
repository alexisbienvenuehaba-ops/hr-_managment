package com.example.rh_managment.controler;

import com.example.rh_managment.dto.request.ContractRequestDto;
import com.example.rh_managment.dto.response.ContractResponseDto;
import com.example.rh_managment.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ContractController {

    private final ContractService service;

    @PostMapping
    public ResponseEntity<ContractResponseDto> create(@Valid @RequestBody ContractRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ContractResponseDto update(@PathVariable Long id, @Valid @RequestBody ContractRequestDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public ContractResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<ContractResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/employee/{idEmployee}")
    public List<ContractResponseDto> findByEmployee(@PathVariable Long idEmployee) {
        return service.findByEmployee(idEmployee);
    }

    @PatchMapping("/{id}/terminate")
    public ContractResponseDto terminate(@PathVariable Long id) {
        return service.terminate(id);
    }
}