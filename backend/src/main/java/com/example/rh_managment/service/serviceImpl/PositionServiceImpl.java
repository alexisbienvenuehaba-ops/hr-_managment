package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.PositionRequestDto;
import com.example.rh_managment.dto.response.PositionResponseDto;
import com.example.rh_managment.entity.PositionEntity;
import com.example.rh_managment.enums.PositionStatus;
import com.example.rh_managment.mapper.PositionMapper;
import com.example.rh_managment.repository.PositionRepository;
import com.example.rh_managment.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;
    private final PositionMapper mapper;

    @Override
    public PositionResponseDto create(PositionRequestDto dto) {
        if (repository.existsByNamePositionIgnoreCase(dto.getNamePosition().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A position with this name already exists");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public PositionResponseDto update(Long id, PositionRequestDto dto) {
        PositionEntity e = getOrThrow(id);
        if (repository.existsByNamePositionIgnoreCaseAndIdPositionNot(dto.getNamePosition().trim(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A position with this name already exists");
        }
        mapper.updateEntity(e, dto);
        return mapper.toResponse(repository.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public PositionResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PositionResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PositionResponseDto> findAllActive() {
        return repository.findAll().stream()
                .filter(p -> p.getStatus() == PositionStatus.ACTIVE)
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void deactivate(Long id) {
        PositionEntity e = getOrThrow(id);
        e.setStatus(PositionStatus.INACTIVE);
        repository.save(e);
    }

    @Override
    public void activate(Long id) {
        PositionEntity e = getOrThrow(id);
        e.setStatus(PositionStatus.ACTIVE);
        repository.save(e);
    }

    private PositionEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Position not found"));
    }
}