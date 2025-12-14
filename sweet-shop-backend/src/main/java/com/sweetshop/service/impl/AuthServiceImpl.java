package com.sweetshop.service.impl;

import com.sweetshop.dto.AuthResponse;
import com.sweetshop.dto.LoginRequest;
import com.sweetshop.dto.RegisterRequest;
import com.sweetshop.exception.InvalidOperationException;
import com.sweetshop.model.Role;
import com.sweetshop.model.User;
import com.sweetshop.repository.UserRepository;
import com.sweetshop.security.JwtUtil;
import com.sweetshop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

   @Override
public void register(RegisterRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
        throw new InvalidOperationException("Email already registered");
    }

    User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole()) // ✅ FIX
            .build();

    userRepository.save(user);
}


    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidOperationException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidOperationException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getRole());
        long expiresAt = System.currentTimeMillis() + jwtUtil.getExpirationMillis();
        return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole(), expiresAt);
    }
}
