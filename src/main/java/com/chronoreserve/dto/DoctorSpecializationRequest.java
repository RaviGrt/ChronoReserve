package com.chronoreserve.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorSpecializationRequest {
    private Long doctorId;
    private Long specializationId;
}
