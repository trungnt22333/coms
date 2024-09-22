package com.example.comstest.repositories;

import com.example.comstest.domain.entities.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
}
