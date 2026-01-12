package com.chronoreserve.repository;

import com.chronoreserve.Entity.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorSpecializationRepository
        extends JpaRepository<DoctorSpecialization, Long> {

    List<DoctorSpecialization> findByDoctorId(Long doctorId);
}
