package com.chronoreserve.repository;

import com.chronoreserve.Entity.Doctor;
import com.chronoreserve.Entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findByDoctorAndSlotDate(Doctor doctor, LocalDate slotDate);

    List<TimeSlot> findByDoctor(Doctor doctor);
}
