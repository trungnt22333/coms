package com.example.comstest.mappers.impl;

import com.example.comstest.domain.dto.ContractDTO;
import com.example.comstest.domain.entities.ContractEntity;
import com.example.comstest.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper implements Mapper<ContractEntity, ContractDTO> {
    private ModelMapper modelMapper;

    public ContractMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ContractDTO mapTo(ContractEntity contractEntity) {
        return modelMapper.map(contractEntity, ContractDTO.class);
    }

    @Override
    public ContractEntity mapFrom(ContractDTO contractDTO) {
        return modelMapper.map(contractDTO, ContractEntity.class);
    }
}
