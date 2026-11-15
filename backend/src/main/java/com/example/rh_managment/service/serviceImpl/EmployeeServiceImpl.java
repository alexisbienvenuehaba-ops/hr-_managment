package com.example.rh_managment.service.serviceImpl;

import com.example.rh_managment.dto.request.EmployeeRequestDto;
import com.example.rh_managment.dto.response.EmployeeResponseDto;
import com.example.rh_managment.entity.EmployeeEntity;
import com.example.rh_managment.mapper.EmployeeMapper;
import com.example.rh_managment.repository.EmployeeRepository;
import com.example.rh_managment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    // Code entreprise provisoire, modifiable dans application.properties : app.company-code=XX
    @Value("${app.company-code:RH}")
    private String companyCode;

    @Override
    public EmployeeResponseDto create(EmployeeRequestDto dto) {
        EmployeeEntity e = mapper.toEntity(dto);
        checkEmailUnique(e.getEmail(), null);
        e.setRegistrationNumber(generateRegistrationNumber());
        return mapper.toResponse(repository.save(e));
    }

    @Override
    public EmployeeResponseDto update(Long id, EmployeeRequestDto dto) {
        EmployeeEntity e = getOrThrow(id);
        mapper.updateEntity(e, dto);
        checkEmailUnique(e.getEmail(), id);
        // Le matricule n'est jamais modifié
        return mapper.toResponse(repository.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDto findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    private void checkEmailUnique(String email, Long currentId) {
        if (email == null) return;
        boolean exists = (currentId == null)
                ? repository.existsByEmailIgnoreCase(email)
                : repository.existsByEmailIgnoreCaseAndIdEmployeeNot(email, currentId);
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "An employee with this email already exists");
        }
    }

    // Format : code entreprise + année sur 2 chiffres + séquence annuelle sur 3 chiffres (ex. RH26001)
    private String generateRegistrationNumber() {
        String year = String.format("%02d", LocalDate.now().getYear() % 100);
        String prefix = companyCode + year;
        long sequence = repository.countByRegistrationNumberStartingWith(prefix) + 1;
        return prefix + String.format("%03d", sequence);
    }

    private EmployeeEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }
}