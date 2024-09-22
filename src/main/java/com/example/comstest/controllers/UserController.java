package com.example.comstest.controllers;

import com.example.comstest.domain.dto.UserDTO;
import com.example.comstest.domain.entities.UserEntity;
import com.example.comstest.mappers.Mapper;
import com.example.comstest.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private UserService userService;
    private Mapper<UserEntity, UserDTO> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDTO> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUserEntity = userService.saveUser(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        Optional<UserEntity> foundUser = userService.getUserById(id);
        return foundUser.map(
                userEntity -> { UserDTO userDTO = userMapper.mapTo(userEntity);
                return new ResponseEntity<>(userDTO, HttpStatus.OK);}
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/users/emails/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email) {
        Optional<UserEntity> foundUser = userService.getUserByEmail(email);

        return foundUser.map(
                userEntity -> { UserDTO userDTO = userMapper.mapTo(userEntity);
                    return new ResponseEntity<>(userDTO, HttpStatus.OK);}
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<UserDTO> updateUserFull(@PathVariable("id") Long id,
                                                  @RequestBody UserDTO user) {
        if(!userService.isUserExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUserEntity = userService.saveUser(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<UserDTO> updateUserPartial(@PathVariable("id") Long id,
                                                  @RequestBody UserDTO user) {
        if(!userService.isUserExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity updatedUser = userService.updateUserPartial(id, userEntity);
        return new ResponseEntity<>(userMapper.mapTo(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
