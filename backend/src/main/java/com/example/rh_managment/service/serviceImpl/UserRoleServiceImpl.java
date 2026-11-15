package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.UserRoleRequestDto;
import com.example.rh_managment.dto.response.UserRoleResponseDto;
import com.example.rh_managment.entity.RoleEntity;
import com.example.rh_managment.entity.UserEntity;
import com.example.rh_managment.entity.UserRoleId;
import com.example.rh_managment.mapper.UserRoleMapper;
import com.example.rh_managment.repository.RoleRepository;
import com.example.rh_managment.repository.UserRepository;
import com.example.rh_managment.repository.UserRoleRepository;
import com.example.rh_managment.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleMapper mapper;

    @Override
    public UserRoleResponseDto assign(UserRoleRequestDto dto) {
        UserEntity user = userRepository.findById(dto.getIdUser()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        RoleEntity role = roleRepository.findById(dto.getIdRole()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));

        if (repository.existsById(buildId(user.getIdUser(), role.getIdRole()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This user already has this role");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(user, role)));
    }

    @Override
    public void remove(Long idUser, Long idRole) {
        UserRoleId id = buildId(idUser, idRole);
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not have this role");
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRoleResponseDto> findByUser(Long idUser) {
        return repository.findByUser_IdUser(idUser).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRoleResponseDto> findByRole(Long idRole) {
        return repository.findByRole_IdRole(idRole).stream().map(mapper::toResponse).toList();
    }

    private UserRoleId buildId(Long idUser, Long idRole) {
        UserRoleId id = new UserRoleId();
        id.setIdUser(idUser);
        id.setIdRole(idRole);
        return id;
    }
}