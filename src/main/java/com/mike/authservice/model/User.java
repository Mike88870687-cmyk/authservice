package com.mike.authservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    private String email;
    private String passwordHash;
    private LocalDateTime createdAt;

    public User(){}

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