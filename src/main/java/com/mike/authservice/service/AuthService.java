package com.mike.authservice.service;

import com.mike.authservice.dto.RegisterRequest;
import com.mike.authservice.dto.AuthResponse;
import com.mike.authservice.model.User;
import com.mike.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        return new AuthResponse("User already exists");
        }

        User user = new User(
                UUID.randomUUID(),
                request.getEmail(),
                request.getPassword(),
                LocalDateTime.now()
        );

        userRepository.save(user);

        return new AuthResponse("User registered successfully");
    }

    public AuthResponse login(RegisterRequest request) {

        var userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            return new AuthResponse("invalid credentials");
        }

        User user = userOptional.get();

        if (!user.getPasswordHash().equals(request.getPassword())) {
            return new AuthResponse("invalid credentials");
        }

        return new AuthResponse("Login successful");
    }
}