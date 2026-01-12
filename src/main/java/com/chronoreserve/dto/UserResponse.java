package com.chronoreserve.dto;

import com.chronoreserve.enums.Role;
import com.chronoreserve.enums.Status;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private Role role;
    private Status status;
}
