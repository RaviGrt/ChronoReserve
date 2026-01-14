package com.chronoreserve.repository;

import com.chronoreserve.Entity.Patient;
import com.chronoreserve.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByUserId(Long userId);

    boolean existsByUser(User user);
}
