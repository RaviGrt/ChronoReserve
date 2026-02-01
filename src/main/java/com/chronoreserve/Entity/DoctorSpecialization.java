package com.chronoreserve.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "doctor_specialization",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_doctor_specialization",
                        columnNames = {"doctor_id", "specialization_id"}
                )
        }
)
@Getter
@Setter
public class DoctorSpecialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;
}
