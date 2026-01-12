package com.chronoreserve.service;

import com.chronoreserve.Entity.User;
import com.chronoreserve.enums.Role;
import com.chronoreserve.enums.Status;
import com.chronoreserve.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("User already exist");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.PATIENT);
        user.setStatus(Status.ACTIVE);

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public void changeStatus(Long userId, Status status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        user.setStatus(status);
        userRepository.save(user);
    }
}
