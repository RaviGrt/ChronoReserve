package com.chronoreserve.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponse {

    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private Long slotId;
    private String slotDate;
    private String startTime;
    private String endTime;
    private String status;
}
