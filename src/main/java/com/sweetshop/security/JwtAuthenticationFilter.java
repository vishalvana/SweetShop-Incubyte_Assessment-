package com.sweetshop.security;

import com.sweetshop.model.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1️⃣ Get Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2️⃣ If no token, continue (Spring Security will block if required)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3️⃣ Extract token
        String token = authHeader.substring(7);

        // 4️⃣ Validate token
        if (!jwtUtil.isTokenValid(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5️⃣ Extract user details from token
        String userId = jwtUtil.extractUserId(token);
        Role role = jwtUtil.extractRole(token);

        // 6️⃣ Create Authentication object
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userId,                      // principal
                        null,                        // credentials (not needed)
                        List.of(new SimpleGrantedAuthority("ROLE_" + role.name()))
                );

        // 7️⃣ Set authentication in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 8️⃣ Continue filter chain
        filterChain.doFilter(request, response);
    }
}
