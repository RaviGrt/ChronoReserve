package com.chronoreserve.dto;

import com.chronoreserve.enums.BloodGroup;
import com.chronoreserve.enums.Status;
import lombok.Data;

@Data
public class PatientResponse {

    private Long id;
    private Long userId;
    private String name;
    private Integer age;
    private BloodGroup bloodGroup;
    private Status status;

}
