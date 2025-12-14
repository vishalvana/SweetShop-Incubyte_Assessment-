package com.sweetshop.controller;

import com.sweetshop.dto.RegisterRequest;
import com.sweetshop.model.Role;
import com.sweetshop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;

    // Only accessible to ADMIN via SecurityConfig path rules
    @PostMapping("/register")
    public void registerAdmin(@RequestBody RegisterRequest request) {
        // Force ADMIN role for this endpoint
        request.setRole(Role.ADMIN);
        authService.register(request);
    }
}
