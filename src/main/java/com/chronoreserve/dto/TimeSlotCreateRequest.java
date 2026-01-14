package com.chronoreserve.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class TimeSlotCreateRequest {
    private LocalDate slotDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
