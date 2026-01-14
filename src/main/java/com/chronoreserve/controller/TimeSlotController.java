package com.chronoreserve.controller;

import com.chronoreserve.Entity.TimeSlot;
import com.chronoreserve.dto.TimeSlotCreateRequest;
import com.chronoreserve.dto.TimeSlotResponse;
import com.chronoreserve.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping("/{doctorId}/slots")
    public ResponseEntity<TimeSlotResponse> createSlot(
            @PathVariable Long doctorId,
            @RequestBody TimeSlotCreateRequest request
    ) {
        TimeSlot slot = timeSlotService.createSlot(
                doctorId,
                request.getSlotDate(),
                request.getStartTime(),
                request.getEndTime()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(slot));
    }

    @GetMapping("/{doctorId}/slots")
    public List<TimeSlotResponse> getSlots(@PathVariable Long doctorId) {
        return timeSlotService.getDoctorSlots(doctorId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TimeSlotResponse toResponse(TimeSlot slot) {
        TimeSlotResponse response = new TimeSlotResponse();
        response.setId(slot.getId());
        response.setSlotDate(slot.getSlotDate());
        response.setStartTime(slot.getStartTime());
        response.setEndTime(slot.getEndTime());
        response.setStatus(slot.getStatus().name());
        return response;
    }
}
