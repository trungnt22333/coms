//package com.example.comstest.controllers;
//
//import com.example.comstest.domain.entities.UserEntity;
//import com.example.comstest.services.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//public class AuthenticationController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public AuthenticationController(UserService userService, PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @PostMapping(path = "/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//        Optional<UserEntity> optionalUserEntity = userService.getUserByEmail(email);
//        if (optionalUserEntity.isPresent()) {
//            UserEntity user = optionalUserEntity.get();
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                return new ResponseEntity<>("Login successful", HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
//    }
//
//
//    @GetMapping("/logout")
//    public ResponseEntity<Void> logout(HttpServletRequest request) {
//        try {
//            request.logout();
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}
