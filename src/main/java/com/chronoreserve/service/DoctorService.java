package com.chronoreserve.service;

import com.chronoreserve.Entity.Doctor;
import com.chronoreserve.Entity.User;
import com.chronoreserve.enums.Role;
import com.chronoreserve.enums.Status;
import com.chronoreserve.repository.DoctorRepository;
import com.chronoreserve.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public Doctor createDoctor(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));

        if (user.getRole() != Role.DOCTOR) {
            throw new IllegalStateException("User is not a doctor");
        }

        if (doctorRepository.existsByUser(user)) {
            throw new IllegalStateException("Doctor already exists for this user");
        }

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setStatus(Status.ACTIVE);

        return doctorRepository.save(doctor);
    }

    public Doctor getDoctor(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalStateException("Doctor not found"));
    }
}
