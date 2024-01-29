package com.todoapplication.service;

import com.todoapplication.model.User;
import com.todoapplication.repository.UserRepository;
import com.todoapplication.jwt.JwtUtils;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String username, String password) {
        userRepository.addUser(username, password);
    }
    
    public String signIn(String username, String password) {
        if (userRepository.isUserExists(username, password)) {
            return userRepository.generateToken(username);
        }
        return null;
    }
}
