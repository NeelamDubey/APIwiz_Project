package com.Assignment.Internship.Assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Assignment.Internship.Assignment.entity.UserEntity;
import com.Assignment.Internship.Assignment.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long findUserIdByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getId();
        } else {
            throw new IllegalArgumentException("User not found for username: " + username);
        }
    }
}
