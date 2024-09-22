package com.example.comstest.services;

import com.example.comstest.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    UserEntity updateUserPartial(Long id, UserEntity user);
    Optional<UserEntity> getUserByEmail(String email);
    Optional<UserEntity> getUserById(Long id);
    List<UserEntity> getAllUsers();
    void deleteUser(Long id);
    boolean isUserExists(Long id);

}
