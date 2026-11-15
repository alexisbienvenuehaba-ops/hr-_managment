package com.example.rh_managment.controller;

import com.example.rh_managment.dto.response.ServiceAssignmentHistoryResponseDto;
import com.example.rh_managment.service.ServiceAssignmentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-histories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceAssignmentHistoryController {

    private final ServiceAssignmentHistoryService service;

    @GetMapping("/service/{idService}")
    public List<ServiceAssignmentHistoryResponseDto> findByService(@PathVariable Long idService) {
        return service.findByService(idService);
    }

    @GetMapping("/service/{idService}/current")
    public ServiceAssignmentHistoryResponseDto findCurrent(@PathVariable Long idService) {
        return service.findCurrentByService(idService);
    }
}