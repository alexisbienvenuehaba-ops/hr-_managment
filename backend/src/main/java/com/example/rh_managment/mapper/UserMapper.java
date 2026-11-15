package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.UserRequestDto;
import com.example.rh_managment.dto.request.UserUpdateRequestDto;
import com.example.rh_managment.dto.response.UserResponseDto;
import com.example.rh_managment.entity.EmployeeEntity;
import com.example.rh_managment.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserRequestDto dto, EmployeeEntity employee, String passwordHash) {
        UserEntity e = new UserEntity();
        e.setEmployee(employee);
        e.setUsernameUser(dto.getUsernameUser().trim());
        e.setPasswordHashUser(passwordHash);
        e.setActiveUser(true);
        e.setPasswordChangeRequiredUser(true);
        return e;
    }

    public void updateEntity(UserEntity e, UserUpdateRequestDto dto) {
        e.setUsernameUser(dto.getUsernameUser().trim());
    }

    // Le mot de passe (même haché) n'est jamais renvoyé
    public UserResponseDto toResponse(UserEntity e) {
        UserResponseDto r = new UserResponseDto();
        r.setIdUser(e.getIdUser());
        if (e.getEmployee() != null) {
            r.setIdEmployee(e.getEmployee().getIdEmployee());
            r.setEmployeeFullName(e.getEmployee().getFirstName() + " " + e.getEmployee().getLastName());
        }
        r.setUsernameUser(e.getUsernameUser());
        r.setActiveUser(e.getActiveUser());
        r.setPasswordChangeRequiredUser(e.getPasswordChangeRequiredUser());
        return r;
    }
}