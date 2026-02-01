package com.chronoreserve.service;

import com.chronoreserve.Entity.Specialization;
import com.chronoreserve.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public Specialization createSpecialization(String name) {

        specializationRepository.findByName(name)
                .ifPresent(s -> {
                    throw new IllegalStateException("Specialization already exists");
                });

        Specialization specialization = new Specialization();
        specialization.setName(name);

        return specializationRepository.save(specialization);
    }

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }
}
