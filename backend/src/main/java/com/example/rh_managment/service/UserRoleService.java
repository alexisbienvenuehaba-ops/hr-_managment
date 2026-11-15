package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.UserRoleRequestDto;
import com.example.rh_managment.dto.response.UserRoleResponseDto;

import java.util.List;

public interface UserRoleService {
    UserRoleResponseDto assign(UserRoleRequestDto dto);
    void remove(Long idUser, Long idRole);
    List<UserRoleResponseDto> findByUser(Long idUser);
    List<UserRoleResponseDto> findByRole(Long idRole);
}