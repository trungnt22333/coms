package com.example.comstest.services.impl;

import com.example.comstest.domain.entities.UserEntity;
import com.example.comstest.repositories.UserRepository;
import com.example.comstest.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initializeData() {
        UserEntity user1 = new UserEntity(1L,"John", "Doe", "john.doe@example.com", "password123", "USER");
        userRepository.save(user1);

        // ... save other users
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUserPartial(Long id, UserEntity user) {
        user.setId(id);
        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(user.getFirstName()).ifPresent(existingUser::setFirstName);
            Optional.ofNullable(user.getLastName()).ifPresent(existingUser::setFirstName);
            Optional.ofNullable(user.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(user.getPassword()).ifPresent(existingUser::setPassword);
            Optional.ofNullable(user.getRole()).ifPresent(existingUser::setRole);
            return userRepository.save(existingUser);

        }).orElseThrow(() -> new RuntimeException("User not found"));

    }


    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isUserExists(Long id) {
        return userRepository.existsById(id);

    }
}
