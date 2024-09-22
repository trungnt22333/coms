package com.example.comstest.services.impl;

import com.example.comstest.domain.entities.ContractEntity;
import com.example.comstest.repositories.ContractRepository;
import com.example.comstest.services.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {
    private ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    @Override
    public ContractEntity saveContract(ContractEntity contract) {
        return contractRepository.save(contract);
    }

    @Override
    public ContractEntity updateContractPartial(Long id, ContractEntity contract) {
        contract.setId(id);
        return contractRepository.findById(id).map(existingcontract -> {
            Optional.ofNullable(contract.getContract_number()).ifPresent(existingcontract::setContract_number);
            Optional.ofNullable(contract.getContract_type()).ifPresent(existingcontract::setContract_type);
            Optional.ofNullable(contract.getStart_date()).ifPresent(existingcontract::setStart_date);
            Optional.ofNullable(contract.getEnd_date()).ifPresent(existingcontract::setEnd_date);
            Optional.ofNullable(contract.getStatus()).ifPresent(existingcontract::setStatus);
            return contractRepository.save(existingcontract);
        }).orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    @Override
    public Optional<ContractEntity> getContractById(Long id) {
        return contractRepository.findById(id);
    }

    @Override
    public List<ContractEntity> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void deleteContract(Long id) {
        contractRepository.deleteById(id);

    }

    @Override
    public boolean isContractExists(Long id) {
        return contractRepository.existsById(id);
    }
}
