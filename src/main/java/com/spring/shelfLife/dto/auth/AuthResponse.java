package com.spring.shelfLife.dto.auth;

public record AuthResponse(
        String token,
        Long userId,
        String email,
        String username
) {
}
