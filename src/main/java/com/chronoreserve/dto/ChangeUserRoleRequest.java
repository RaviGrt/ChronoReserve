package com.chronoreserve.dto;

import com.chronoreserve.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeUserRoleRequest {
    private Role role;
}
