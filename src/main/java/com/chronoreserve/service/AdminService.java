package com.chronoreserve.service;

import com.chronoreserve.Entity.Doctor;
import com.chronoreserve.Entity.User;
import com.chronoreserve.enums.Role;
import com.chronoreserve.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final DoctorService doctorService;

    public AdminService(UserRepository userRepository,
                        DoctorService doctorService) {
        this.userRepository = userRepository;
        this.doctorService = doctorService;
    }

    public User changeUserRole(Long userId, Role role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        user.setRole(role);
        return userRepository.save(user);
    }

    public Doctor createDoctorProfile(Long userId) {
        return doctorService.createDoctor(userId);
    }
}
