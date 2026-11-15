package com.example.rh_managment.service;

import com.example.rh_managment.dto.request.UserPasswordRequestDto;
import com.example.rh_managment.dto.request.UserRequestDto;
import com.example.rh_managment.dto.request.UserUpdateRequestDto;
import com.example.rh_managment.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto create(UserRequestDto dto);
    UserResponseDto update(Long id, UserUpdateRequestDto dto);
    UserResponseDto findById(Long id);
    List<UserResponseDto> findAll();
    void activate(Long id);
    void deactivate(Long id);
    void changePassword(Long id, UserPasswordRequestDto dto);
    void resetPassword(Long id, UserPasswordRequestDto dto);
}