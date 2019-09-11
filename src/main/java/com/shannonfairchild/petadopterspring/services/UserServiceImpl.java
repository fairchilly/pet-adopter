package com.shannonfairchild.petadopterspring.services;

import com.shannonfairchild.petadopterspring.model.User;
import com.shannonfairchild.petadopterspring.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
