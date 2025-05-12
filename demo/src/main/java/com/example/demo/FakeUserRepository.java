package com.example.demo;


import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FakeUserRepository {
    private final Map<String, UserEntity> users = new ConcurrentHashMap<>();

    public UserEntity findByUsername(String username) {
        return users.get(username);
    }

    public void save(UserEntity user) {
        users.put(user.getUsername(), user);
    }

    public boolean exists(String username) {
        return users.containsKey(username);
    }
}