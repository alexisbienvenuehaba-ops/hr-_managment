package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.response.UserRoleResponseDto;
import com.example.rh_managment.entity.RoleEntity;
import com.example.rh_managment.entity.UserEntity;
import com.example.rh_managment.entity.UserRoleEntity;
import com.example.rh_managment.entity.UserRoleId;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    public UserRoleEntity toEntity(UserEntity user, RoleEntity role) {
        UserRoleId id = new UserRoleId();
        id.setIdUser(user.getIdUser());
        id.setIdRole(role.getIdRole());

        UserRoleEntity e = new UserRoleEntity();
        e.setId(id);
        e.setUser(user);
        e.setRole(role);
        return e;
    }

    public UserRoleResponseDto toResponse(UserRoleEntity e) {
        UserRoleResponseDto r = new UserRoleResponseDto();
        r.setIdUser(e.getUser().getIdUser());
        r.setUsernameUser(e.getUser().getUsernameUser());
        r.setIdRole(e.getRole().getIdRole());
        r.setNameRole(e.getRole().getNameRole());
        return r;
    }
}