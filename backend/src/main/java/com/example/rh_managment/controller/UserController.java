package com.example.rh_managment.controller;

import com.example.rh_managment.dto.request.UserPasswordRequestDto;
import com.example.rh_managment.dto.request.UserRequestDto;
import com.example.rh_managment.dto.request.UserUpdateRequestDto;
import com.example.rh_managment.dto.response.UserResponseDto;
import com.example.rh_managment.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        return service.findAll();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        service.activate(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id,
                                               @Valid @RequestBody UserPasswordRequestDto dto) {
        service.changePassword(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reset-password")
    public ResponseEntity<Void> resetPassword(@PathVariable Long id,
                                              @Valid @RequestBody UserPasswordRequestDto dto) {
        service.resetPassword(id, dto);
        return ResponseEntity.noContent().build();
    }
}