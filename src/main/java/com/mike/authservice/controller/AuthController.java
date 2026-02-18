package com.mike.authservice.controller;

import com.mike.authservice.dto.AuthResponse;
import com.mike.authservice.dto.RegisterRequest;
import com.mike.authservice.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public  AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody RegisterRequest request) {
        return authService.login(request);
    }
}
