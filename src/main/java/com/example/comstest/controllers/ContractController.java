package com.example.comstest.controllers;

import com.example.comstest.domain.dto.ContractDTO;
import com.example.comstest.domain.dto.UserDTO;
import com.example.comstest.domain.entities.ContractEntity;
import com.example.comstest.domain.entities.UserEntity;
import com.example.comstest.mappers.Mapper;
import com.example.comstest.services.ContractService;
import com.example.comstest.services.impl.ContractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ContractController {
    private ContractService contractService;
    private Mapper<ContractEntity, ContractDTO> mapper;


    public ContractController(ContractService contractService, Mapper<ContractEntity, ContractDTO> mapper) {
        this.contractService = contractService;
        this.mapper = mapper;
    }

    @PostMapping(path = "/contracts")
    public ResponseEntity<ContractDTO> createContract(@RequestBody ContractDTO contract) {
        ContractEntity contractEntity = mapper.mapFrom(contract);
        ContractEntity savedContractEntity = contractService.saveContract(contractEntity);
        return new ResponseEntity<>(mapper.mapTo(savedContractEntity), HttpStatus.CREATED);
    }

    @PutMapping(path = "/contracts/{id}")
    public ResponseEntity<ContractDTO> updateContractFull(@PathVariable("id") Long id,
                                                  @RequestBody ContractDTO contract) {
        if(!contractService.isContractExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contract.setId(id);
        ContractEntity contractEntity = mapper.mapFrom(contract);
        ContractEntity savedContractEntity = contractService.saveContract(contractEntity);
        return new ResponseEntity<>(mapper.mapTo(savedContractEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/contracts/{id}")
    public ResponseEntity<ContractDTO> updateUserPartial(@PathVariable("id") Long id,
                                                     @RequestBody ContractDTO contract) {
        if(!contractService.isContractExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contract.setId(id);
        ContractEntity contractEntity = mapper.mapFrom(contract);
        ContractEntity updatedContract = contractService.updateContractPartial(id, contractEntity);
        return new ResponseEntity<>(mapper.mapTo(updatedContract), HttpStatus.OK);
    }

    @GetMapping(path = "/contracts")
    public List<ContractDTO> getContracts() {
        List<ContractEntity> contracts = contractService.getAllContracts();
        return contracts.stream()
                .map(mapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/contracts/{id}")
    public ResponseEntity<ContractDTO> getContract(@PathVariable("id") Long id) {
        Optional<ContractEntity> contract = contractService.getContractById(id);
        return contract.map(contractEntity -> {
            ContractDTO contractDTO = mapper.mapTo(contractEntity);
            return ResponseEntity.ok(contractDTO);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/contracts/{id}")
    public ResponseEntity deleteContract(@PathVariable("id") Long id) {
        contractService.deleteContract(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
