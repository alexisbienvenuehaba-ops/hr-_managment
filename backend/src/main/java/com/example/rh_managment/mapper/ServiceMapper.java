package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.ServiceRequestDto;
import com.example.rh_managment.dto.response.ServiceResponseDto;
import com.example.rh_managment.entity.DepartmentEntity;
import com.example.rh_managment.entity.ServiceEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public ServiceEntity toEntity(ServiceRequestDto dto, DepartmentEntity department) {
        ServiceEntity e = new ServiceEntity();
        updateEntity(e, dto, department);
        return e;
    }

    public void updateEntity(ServiceEntity e, ServiceRequestDto dto, DepartmentEntity department) {
        e.setDepartment(department);
        e.setNameService(dto.getNameService().trim());
        e.setDescriptionService(
                dto.getDescriptionService() == null ? null : dto.getDescriptionService().trim());
    }

    public ServiceResponseDto toResponse(ServiceEntity e) {
        ServiceResponseDto r = new ServiceResponseDto();
        r.setIdService(e.getIdService());
        r.setIdDepartment(e.getDepartment().getIdDepartment());
        r.setDepartmentName(e.getDepartment().getName());
        r.setNameService(e.getNameService());
        r.setDescriptionService(e.getDescriptionService());
        r.setStatusService(e.getStatusService());
        r.setCreatedAtService(e.getCreatedAtService());
        r.setUpdatedAtService(e.getUpdatedAtService());
        return r;
    }
}