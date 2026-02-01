package com.chronoreserve.controller;

import com.chronoreserve.Entity.Appointment;
import com.chronoreserve.dto.AppointmentResponse;
import com.chronoreserve.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // ================= BOOK =================
    @PostMapping("/book")
    public ResponseEntity<AppointmentResponse> bookAppointment(
            @RequestParam Long patientId,
            @RequestParam Long slotId
    ) {
        Appointment appointment = appointmentService.bookAppointment(patientId, slotId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapToResponse(appointment));
    }

    @PutMapping("/{appointmentId}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok("Appointment cancelled successfully");
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByPatient(
            @PathVariable Long patientId) {

        List<AppointmentResponse> response = appointmentService
                .getAppointmentsByPatient(patientId)
                .stream()
                .map(this::mapToResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    // ================= MAPPER =================
    private AppointmentResponse mapToResponse(Appointment appointment) {

        AppointmentResponse response = new AppointmentResponse();
        response.setAppointmentId(appointment.getId());
        response.setPatientId(appointment.getPatient().getId());
        response.setDoctorId(appointment.getTimeSlot().getDoctor().getId());
        response.setSlotId(appointment.getTimeSlot().getId());
        response.setSlotDate(appointment.getTimeSlot().getSlotDate().toString());
        response.setStartTime(appointment.getTimeSlot().getStartTime().toString());
        response.setEndTime(appointment.getTimeSlot().getEndTime().toString());
        response.setStatus(appointment.getStatus().name());

        return response;
    }
}
