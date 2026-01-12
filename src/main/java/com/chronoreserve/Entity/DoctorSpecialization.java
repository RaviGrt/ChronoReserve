package com.chronoreserve.Entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "doctor_specialization",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"doctor_id", "specialization_id"})
        }
)
public class DoctorSpecialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;
}
