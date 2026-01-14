package com.chronoreserve.service;

import com.chronoreserve.Entity.Patient;
import com.chronoreserve.Entity.User;
import com.chronoreserve.enums.BloodGroup;
import com.chronoreserve.enums.Status;
import com.chronoreserve.repository.PatientRepository;
import com.chronoreserve.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientService(PatientRepository patientRepository,
                          UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public Patient createPatient(Long userId,
                                 String name,
                                 Integer age,
                                 BloodGroup bloodGroup) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        if (patientRepository.existsByUser(user)) {
            throw new IllegalStateException("Patient already exists for this user");
        }

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setName(name);
        patient.setAge(age);
        patient.setBloodGroup(bloodGroup);
        patient.setStatus(Status.ACTIVE);

        return patientRepository.save(patient);
    }

    public Patient getPatient(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalStateException("Patient not found"));
    }
}
