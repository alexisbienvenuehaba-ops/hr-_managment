package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.response.ServiceAssignmentHistoryResponseDto;
import com.example.rh_managment.entity.ServiceAssignmentHistoryEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceAssignmentHistoryMapper {

    public ServiceAssignmentHistoryResponseDto toResponse(ServiceAssignmentHistoryEntity e) {
        ServiceAssignmentHistoryResponseDto r = new ServiceAssignmentHistoryResponseDto();
        r.setIdServiceAssignmentHistory(e.getIdServiceAssignmentHistory());
        r.setIdService(e.getService().getIdService());
        r.setNameService(e.getService().getNameService());
        r.setIdDepartment(e.getDepartment().getIdDepartment());
        r.setDepartmentName(e.getDepartment().getName());
        r.setStartDateHistory(e.getStartDateHistory());
        r.setEndDateHistory(e.getEndDateHistory());
        return r;
    }
}