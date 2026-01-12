package com.chronoreserve.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;
    private String password;
}
