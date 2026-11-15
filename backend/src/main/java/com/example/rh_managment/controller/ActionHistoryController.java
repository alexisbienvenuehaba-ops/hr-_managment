package com.example.rh_managment.controler;

import com.example.rh_managment.dto.request.ActionHistoryRequestDto;
import com.example.rh_managment.dto.response.ActionHistoryResponseDto;
import com.example.rh_managment.service.ActionHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/action-histories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ActionHistoryController {

    private final ActionHistoryService service;

    // TEMPORAIRE : pour les tests, à retirer quand la connexion existera
    @PostMapping
    public ResponseEntity<ActionHistoryResponseDto> record(@Valid @RequestBody ActionHistoryRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.record(dto));
    }

    @GetMapping
    public List<ActionHistoryResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/user/{idUser}")
    public List<ActionHistoryResponseDto> findByUser(@PathVariable Long idUser) {
        return service.findByUser(idUser);
    }

    @GetMapping("/entity/{entityName}/{entityId}")
    public List<ActionHistoryResponseDto> findByEntity(@PathVariable String entityName,
                                                       @PathVariable Long entityId) {
        return service.findByEntity(entityName, entityId);
    }
}