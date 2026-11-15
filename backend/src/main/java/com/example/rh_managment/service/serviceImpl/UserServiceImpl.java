package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.UserPasswordRequestDto;
import com.example.rh_managment.dto.request.UserRequestDto;
import com.example.rh_managment.dto.request.UserUpdateRequestDto;
import com.example.rh_managment.dto.response.UserResponseDto;
import com.example.rh_managment.entity.EmployeeEntity;
import com.example.rh_managment.entity.UserEntity;
import com.example.rh_managment.enums.EmployeeStatus;
import com.example.rh_managment.mapper.UserMapper;
import com.example.rh_managment.repository.EmployeeRepository;
import com.example.rh_managment.repository.UserRepository;
import com.example.rh_managment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final EmployeeRepository employeeRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto create(UserRequestDto dto) {
        EmployeeEntity employee = null;
        if (dto.getIdEmployee() != null) {
            employee = employeeRepository.findById(dto.getIdEmployee()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
            if (employee.getEmployeeStatus() != EmployeeStatus.ACTIVE) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This employee has left the company");
            }
            if (repository.existsByEmployee_IdEmployee(employee.getIdEmployee())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This employee already has an account");
            }
        }
        if (repository.existsByUsernameUserIgnoreCase(dto.getUsernameUser().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is already taken");
        }
        String hash = passwordEncoder.encode(dto.getPasswordUser());
        return mapper.toResponse(repository.save(mapper.toEntity(dto, employee, hash)));
    }

    @Override
    public UserResponseDto update(Long id, UserUpdateRequestDto dto) {
        UserEntity e = getOrThrow(id);
        if (repository.existsByUsernameUserIgnoreCaseAndIdUserNot(dto.getUsernameUser().trim(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is already taken");
        }
        mapper.updateEntity(e, dto);
        return mapper.toResponse(repository.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public void activate(Long id) {
        UserEntity e = getOrThrow(id);
        if (e.getEmployee() != null && e.getEmployee().getEmployeeStatus() != EmployeeStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Cannot activate the account of an employee who has left the company");
        }
        e.setActiveUser(true);
        repository.save(e);
    }

    @Override
    public void deactivate(Long id) {
        UserEntity e = getOrThrow(id);
        e.setActiveUser(false);
        repository.save(e);
    }

    // L'utilisateur change son mot de passe : l'ancien est vérifié, le changement n'est plus exigé
    @Override
    public void changePassword(Long id, UserPasswordRequestDto dto) {
        UserEntity e = getOrThrow(id);
        if (dto.getOldPasswordUser() == null
                || !passwordEncoder.matches(dto.getOldPasswordUser(), e.getPasswordHashUser())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The current password is incorrect");
        }
        if (dto.getOldPasswordUser().equals(dto.getNewPasswordUser())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The new password must be different from the current one");
        }
        e.setPasswordHashUser(passwordEncoder.encode(dto.getNewPasswordUser()));
        e.setPasswordChangeRequiredUser(false);
        repository.save(e);
    }

    // Réinitialisation par l'administrateur : mot de passe provisoire, à changer à la prochaine connexion
    @Override
    public void resetPassword(Long id, UserPasswordRequestDto dto) {
        UserEntity e = getOrThrow(id);
        e.setPasswordHashUser(passwordEncoder.encode(dto.getNewPasswordUser()));
        e.setPasswordChangeRequiredUser(true);
        repository.save(e);
    }

    private UserEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}