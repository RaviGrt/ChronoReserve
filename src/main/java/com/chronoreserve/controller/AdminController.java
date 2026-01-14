package com.chronoreserve.controller;

import com.chronoreserve.Entity.Doctor;
import com.chronoreserve.Entity.User;
import com.chronoreserve.dto.AdminDoctorCreateRequest;
import com.chronoreserve.dto.ChangeUserRoleRequest;
import com.chronoreserve.dto.DoctorResponse;
import com.chronoreserve.dto.UserResponse;
import com.chronoreserve.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserResponse> changeUserRole(
            @PathVariable Long id,
            @RequestBody ChangeUserRoleRequest request
    ) {
        User user = adminService.changeUserRole(id, request.getRole());

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setStatus(user.getStatus());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/doctors")
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody AdminDoctorCreateRequest request) {
        Doctor doctor = adminService.createDoctorProfile(request.getUserId());

        DoctorResponse response = new DoctorResponse();
        response.setDoctorId(doctor.getId());
        response.setUserId(doctor.getUser().getId());
        response.setEmail(doctor.getUser().getEmail());
        response.setStatus(doctor.getStatus());

        return ResponseEntity.ok(response);
    }
}
