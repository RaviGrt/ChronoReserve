package com.chronoreserve.service;

import com.chronoreserve.Entity.Appointment;
import com.chronoreserve.Entity.Patient;
import com.chronoreserve.Entity.TimeSlot;
import com.chronoreserve.enums.AppointmentStatus;
import com.chronoreserve.enums.SlotStatus;
import com.chronoreserve.repository.AppointmentRepository;
import com.chronoreserve.repository.PatientRepository;
import com.chronoreserve.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final TimeSlotRepository timeSlotRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              TimeSlotRepository timeSlotRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Transactional
    public Appointment bookAppointment(Long patientId, Long slotId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalStateException("Patient not found"));

        TimeSlot slot = timeSlotRepository.findById(slotId)
                .orElseThrow(() -> new IllegalStateException("Time slot not found"));

        if (slot.getStatus() != SlotStatus.AVAILABLE) {
            throw new IllegalStateException("Time slot is not available");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setTimeSlot(slot);
        appointment.setStatus(AppointmentStatus.BOOKED);

        slot.setStatus(SlotStatus.BOOKED);

        timeSlotRepository.save(slot);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public void cancelAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalStateException("Appointment not found"));

        if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
            throw new IllegalStateException("Appointment already cancelled");
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);

        TimeSlot slot = appointment.getTimeSlot();
        slot.setStatus(SlotStatus.AVAILABLE);

        timeSlotRepository.save(slot);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {

        if (!patientRepository.existsById(patientId)) {
            throw new IllegalStateException("Patient not found");
        }

        return appointmentRepository.findByPatientId(patientId);
    }
}
