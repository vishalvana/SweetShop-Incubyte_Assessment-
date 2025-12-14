package com.sweetshop.dto;

import com.sweetshop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String userId;
    private String email;
    private Role role;
    // expiration as epoch millis
    private long expiresAt;

    public AuthResponse(String token) {
        this.token = token;
    }
}
