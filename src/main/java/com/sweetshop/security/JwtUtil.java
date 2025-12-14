package com.sweetshop.security;

import com.sweetshop.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // NOTE: For production, store this in environment variables or a vault.
    // Must be at least 32 bytes for HS256.
    private static final String SECRET =
            "change_this_to_a_very_long_and_random_secret_key_which_is_at_least_32_bytes!";
    private static final long EXPIRATION = 1000 * 60 * 60 * 10; // 10 hours

    private final SecretKey key;

    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // --------------------
    // TOKEN GENERATION
    // --------------------
    public String generateToken(String userId, Role role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Expose expiration millis so callers (e.g., AuthService) can return it in responses
    public long getExpirationMillis() {
        return EXPIRATION;
    }

    // --------------------
    // TOKEN VALIDATION
    // --------------------
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserId(String token) {
        return extractClaims(token).getSubject();
    }

    public Role extractRole(String token) {
        String role = extractClaims(token).get("role", String.class);
        return Role.valueOf(role);
    }

    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
