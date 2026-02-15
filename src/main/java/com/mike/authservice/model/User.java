package com.mike.authservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private UUID id;
    private String email;
    private String passwordHash;
    private LocalDateTime createdAt;

    public User(UUID id, String email, String passwordHash, LocalDateTime createdAt){
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public UUID getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
}