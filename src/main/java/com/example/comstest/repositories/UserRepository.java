package com.example.comstest.repositories;

import com.example.comstest.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByFirstName(String firstName);
    UserEntity findByEmail(String email);
}
