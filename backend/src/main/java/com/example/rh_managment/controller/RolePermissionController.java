package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.RolePermissionRequestDto;
import com.example.rh_managment.dto.response.RolePermissionResponseDto;
import com.example.rh_managment.service.RolePermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RolePermissionController {

    private final RolePermissionService service;

    @PostMapping
    public ResponseEntity<RolePermissionResponseDto> assign(@Valid @RequestBody RolePermissionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.assign(dto));
    }

    @DeleteMapping("/role/{idRole}/permission/{idPermission}")
    public ResponseEntity<Void> remove(@PathVariable Long idRole, @PathVariable Long idPermission) {
        service.remove(idRole, idPermission);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/{idRole}")
    public List<RolePermissionResponseDto> findByRole(@PathVariable Long idRole) {
        return service.findByRole(idRole);
    }

    @GetMapping("/permission/{idPermission}")
    public List<RolePermissionResponseDto> findByPermission(@PathVariable Long idPermission) {
        return service.findByPermission(idPermission);
    }
}