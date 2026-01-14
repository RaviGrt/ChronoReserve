package com.chronoreserve.dto;

import com.chronoreserve.enums.BloodGroup;
import lombok.Data;

@Data
public class PatientCreateRequest {

    private Long userId;
    private String name;
    private Integer age;
    private BloodGroup bloodGroup;

}
