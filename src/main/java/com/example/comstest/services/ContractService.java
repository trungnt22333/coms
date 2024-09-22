package com.example.comstest.services;

import com.example.comstest.domain.entities.ContractEntity;
import com.example.comstest.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ContractService {

    ContractEntity saveContract(ContractEntity contract);

    ContractEntity updateContractPartial(Long id, ContractEntity contract);

    Optional<ContractEntity> getContractById(Long id);

    List<ContractEntity> getAllContracts();

    void deleteContract(Long id);

    boolean isContractExists(Long id);
}
