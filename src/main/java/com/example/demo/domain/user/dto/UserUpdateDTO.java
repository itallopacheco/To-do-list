package com.example.demo.domain.user.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;

public record UserUpdateDTO(
        @Nullable
        String name,
        @Nullable
        String username,
        @Nullable
        @Email(message = "Email format is invalid")
        String email
) {
}
