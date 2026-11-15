package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.UserRoleRequestDto;
import com.example.rh_managment.dto.response.UserRoleResponseDto;
import com.example.rh_managment.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserRoleController {

    private final UserRoleService service;

    @PostMapping
    public ResponseEntity<UserRoleResponseDto> assign(@Valid @RequestBody UserRoleRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.assign(dto));
    }

    @DeleteMapping("/user/{idUser}/role/{idRole}")
    public ResponseEntity<Void> remove(@PathVariable Long idUser, @PathVariable Long idRole) {
        service.remove(idUser, idRole);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{idUser}")
    public List<UserRoleResponseDto> findByUser(@PathVariable Long idUser) {
        return service.findByUser(idUser);
    }

    @GetMapping("/role/{idRole}")
    public List<UserRoleResponseDto> findByRole(@PathVariable Long idRole) {
        return service.findByRole(idRole);
    }
}