package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.ActionHistoryRequestDto;
import com.example.rh_managment.dto.response.ActionHistoryResponseDto;
import com.example.rh_managment.entity.UserEntity;
import com.example.rh_managment.mapper.ActionHistoryMapper;
import com.example.rh_managment.repository.ActionHistoryRepository;
import com.example.rh_managment.repository.UserRepository;
import com.example.rh_managment.service.ActionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ActionHistoryServiceImpl implements ActionHistoryService {

    private final ActionHistoryRepository repository;
    private final UserRepository userRepository;
    private final ActionHistoryMapper mapper;

    @Override
    public ActionHistoryResponseDto record(ActionHistoryRequestDto dto) {
        UserEntity user = userRepository.findById(dto.getIdUser()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return mapper.toResponse(repository.save(mapper.toEntity(dto, user)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActionHistoryResponseDto> findAll() {
        return repository.findAllByOrderByActionDateHistoryDesc().stream()
                .map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActionHistoryResponseDto> findByUser(Long idUser) {
        return repository.findByUser_IdUserOrderByActionDateHistoryDesc(idUser).stream()
                .map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActionHistoryResponseDto> findByEntity(String entityName, Long entityId) {
        return repository
                .findByEntityNameHistoryIgnoreCaseAndEntityIdHistoryOrderByActionDateHistoryDesc(entityName, entityId)
                .stream().map(mapper::toResponse).toList();
    }
}