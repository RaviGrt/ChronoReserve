package com.chronoreserve.service;

import com.chronoreserve.Entity.Doctor;
import com.chronoreserve.Entity.DoctorSpecialization;
import com.chronoreserve.Entity.Specialization;
import com.chronoreserve.repository.DoctorRepository;
import com.chronoreserve.repository.DoctorSpecializationRepository;
import com.chronoreserve.repository.SpecializationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorSpecializationService {

    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;

    public DoctorSpecializationService(
            DoctorRepository doctorRepository,
            SpecializationRepository specializationRepository,
            DoctorSpecializationRepository doctorSpecializationRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.specializationRepository = specializationRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
    }

    @Transactional
    public void assignSpecialization(Long doctorId, Long specializationId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalStateException("Doctor not found"));

        Specialization specialization = specializationRepository.findById(specializationId)
                .orElseThrow(() -> new IllegalStateException("Specialization not found"));

        if (doctorSpecializationRepository
                .existsByDoctorIdAndSpecializationId(doctorId, specializationId)) {
            throw new IllegalStateException("Doctor already has this specialization");
        }

        DoctorSpecialization mapping = new DoctorSpecialization();
        mapping.setDoctor(doctor);
        mapping.setSpecialization(specialization);

        doctorSpecializationRepository.save(mapping);
    }

    public List<DoctorSpecialization> getSpecializationsByDoctor(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalStateException("Doctor not found"));

        return doctorSpecializationRepository.findByDoctor(doctor);
    }
}
