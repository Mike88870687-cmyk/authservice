package com.mike.authservice.repository;

import com.mike.authservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final Map<String, User> users = new HashMap<>();

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }

    public void save(User user) {
        users.put(user.getEmail(),user);
    }
}
