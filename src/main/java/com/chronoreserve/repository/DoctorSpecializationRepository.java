package com.chronoreserve.repository;

import com.chronoreserve.Entity.Doctor;
import com.chronoreserve.Entity.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorSpecializationRepository
        extends JpaRepository<DoctorSpecialization, Long> {

    boolean existsByDoctorIdAndSpecializationId(Long doctorId, Long specializationId);

    List<DoctorSpecialization> findByDoctor(Doctor doctor);
}
