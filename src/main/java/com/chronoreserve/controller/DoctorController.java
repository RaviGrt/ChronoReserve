package com.chronoreserve.controller;

import com.chronoreserve.Entity.Doctor;
import com.chronoreserve.dto.DoctorCreateRequest;
import com.chronoreserve.dto.DoctorResponse;
import com.chronoreserve.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody DoctorCreateRequest request) {
        Doctor doctor = doctorService.createDoctor(request.getUserId());
        return new ResponseEntity<>(mapToResponse(doctor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctor(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctor(id);
        return ResponseEntity.ok(mapToResponse(doctor));
    }

    private DoctorResponse mapToResponse(Doctor doctor) {
        DoctorResponse response = new DoctorResponse();
        response.setDoctorId(doctor.getId());
        response.setUserId(doctor.getUser().getId());
        response.setEmail(doctor.getUser().getEmail());
        response.setStatus(doctor.getStatus());
        return response;
    }
}
