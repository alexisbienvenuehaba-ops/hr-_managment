package com.example.rh_managment.service;

import com.example.rh_managment.dto.response.ServiceAssignmentHistoryResponseDto;
import com.example.rh_managment.entity.DepartmentEntity;
import com.example.rh_managment.entity.ServiceEntity;

import java.util.List;

public interface ServiceAssignmentHistoryService {

    // Utilisé par le module Service (pas exposé dans l'API)
    void openInitial(ServiceEntity service, DepartmentEntity department);
    void changeDepartment(ServiceEntity service, DepartmentEntity newDepartment);

    // Consultation
    List<ServiceAssignmentHistoryResponseDto> findByService(Long idService);
    ServiceAssignmentHistoryResponseDto findCurrentByService(Long idService);
}