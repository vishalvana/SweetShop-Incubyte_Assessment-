package com.sweetshop.dto;
import com.sweetshop.model.Role;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
}
