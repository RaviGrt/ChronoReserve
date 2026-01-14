package com.chronoreserve.controller;

import com.chronoreserve.Entity.Patient;
import com.chronoreserve.dto.PatientCreateRequest;
import com.chronoreserve.dto.PatientResponse;
import com.chronoreserve.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientCreateRequest request) {
        Patient patient = patientService.createPatient(
                request.getUserId(),
                request.getName(),
                request.getAge(),
                request.getBloodGroup()
        );

        return new ResponseEntity<>(toResponse(patient), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatient(id);
        return ResponseEntity.ok(toResponse(patient));
    }

    private PatientResponse toResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setUserId(patient.getUser().getId());
        response.setName(patient.getName());
        response.setAge(patient.getAge());
        response.setBloodGroup(patient.getBloodGroup());
        response.setStatus(patient.getStatus());
        return response;
    }
}
