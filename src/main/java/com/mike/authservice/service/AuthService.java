package com.mike.authservice.service;

import com.mike.authservice.dto.RegisterRequest;
import com.mike.authservice.dto.AuthResponse;
import com.mike.authservice.model.User;
import com.mike.authservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<AuthResponse> register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new AuthResponse("User already exists"));
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                UUID.randomUUID(),
                request.getEmail(),
                encodedPassword,
                LocalDateTime.now()
        );

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse("User registered successfully"));
    }

    public ResponseEntity<AuthResponse> login(RegisterRequest request) {

        var userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("invalid credentials"));
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("invalid credentials"));
        }

        return ResponseEntity.ok(
                new AuthResponse("Login successful"));
    }
}