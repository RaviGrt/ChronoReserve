package com.chronoreserve.controller;

import com.chronoreserve.Entity.DoctorSpecialization;
import com.chronoreserve.dto.DoctorSpecializationRequest;
import com.chronoreserve.dto.SpecializationResponse;
import com.chronoreserve.service.DoctorSpecializationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor-specializations")
public class DoctorSpecializationController {

    private final DoctorSpecializationService doctorSpecializationService;

    public DoctorSpecializationController(
            DoctorSpecializationService doctorSpecializationService) {
        this.doctorSpecializationService = doctorSpecializationService;
    }

    @PostMapping
    public ResponseEntity<String> assignSpecialization(
            @RequestBody DoctorSpecializationRequest request) {

        doctorSpecializationService.assignSpecialization(
                request.getDoctorId(),
                request.getSpecializationId()
        );

        return ResponseEntity.ok("Specialization assigned to doctor");
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<SpecializationResponse>> getDoctorSpecializations(
            @PathVariable Long doctorId) {

        List<SpecializationResponse> response =
                doctorSpecializationService.getSpecializationsByDoctor(doctorId)
                        .stream()
                        .map(ds -> {
                            SpecializationResponse r = new SpecializationResponse();
                            r.setId(ds.getSpecialization().getId());
                            r.setName(ds.getSpecialization().getName());
                            return r;
                        })
                        .toList();

        return ResponseEntity.ok(response);
    }
}
