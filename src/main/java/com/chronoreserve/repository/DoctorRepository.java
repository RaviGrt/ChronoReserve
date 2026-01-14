package com.chronoreserve.repository;

import com.chronoreserve.Entity.Doctor;
import com.chronoreserve.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByUserId(Long userId);

    boolean existsByUser(User user);
}
