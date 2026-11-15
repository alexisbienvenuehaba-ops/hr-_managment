package com.example.rh_managment.repository;

import com.example.rh_managment.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    boolean existsByReferenceContractIgnoreCase(String referenceContract);
    boolean existsByReferenceContractIgnoreCaseAndIdContractNot(String referenceContract, Long idContract);
    List<ContractEntity> findByEmployee_IdEmployee(Long idEmployee);
    List<ContractEntity> findByEmployee_IdEmployeeOrderByStartDateContractDesc(Long idEmployee);
}