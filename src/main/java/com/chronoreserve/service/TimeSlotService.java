package com.chronoreserve.service;

import com.chronoreserve.Entity.Doctor;
import com.chronoreserve.Entity.TimeSlot;
import com.chronoreserve.enums.SlotStatus;
import com.chronoreserve.repository.DoctorRepository;
import com.chronoreserve.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final DoctorRepository doctorRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository,
                           DoctorRepository doctorRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.doctorRepository = doctorRepository;
    }

    public TimeSlot createSlot(
            Long doctorId,
            LocalDate slotDate,
            LocalTime startTime,
            LocalTime endTime
    ) {

        if (!startTime.isBefore(endTime)) {
            throw new IllegalStateException("Start time must be before end time");
        }

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalStateException("Doctor not found"));

        List<TimeSlot> existingSlots =
                timeSlotRepository.findByDoctorAndSlotDate(doctor, slotDate);

        for (TimeSlot slot : existingSlots) {
            boolean overlap =
                    startTime.isBefore(slot.getEndTime()) &&
                            endTime.isAfter(slot.getStartTime());

            if (overlap) {
                throw new IllegalStateException("Time slot overlaps with existing slot");
            }
        }

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDoctor(doctor);
        timeSlot.setSlotDate(slotDate);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlot.setStatus(SlotStatus.AVAILABLE);

        return timeSlotRepository.save(timeSlot);
    }

    public List<TimeSlot> getDoctorSlots(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalStateException("Doctor not found"));

        return timeSlotRepository.findByDoctor(doctor);
    }
}
