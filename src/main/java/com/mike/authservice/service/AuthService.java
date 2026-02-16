package com.mike.authservice.service;

import com.mike.authservice.dto.RegisterRequest;
import com.mike.authservice.dto.AuthResponse;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public AuthResponse register(RegisterRequest request) {
        return new AuthResponse("User registered successfully");
    }
}