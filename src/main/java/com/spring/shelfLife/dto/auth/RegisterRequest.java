package com.spring.shelfLife.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(min=8, message = "Password must be min. 8 characters") String password,
        @NotBlank String username

) {
}
