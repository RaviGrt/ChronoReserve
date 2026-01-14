package com.chronoreserve.dto;

import com.chronoreserve.enums.Status;

public class DoctorResponse {

    private Long doctorId;
    private Long userId;
    private String email;
    private Status status;

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
