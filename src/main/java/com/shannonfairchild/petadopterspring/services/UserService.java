package com.shannonfairchild.petadopterspring.services;

import com.shannonfairchild.petadopterspring.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    User save(User user);
}
