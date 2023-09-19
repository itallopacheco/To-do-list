package com.example.demo.domain.user.dto;

import jakarta.validation.constraints.Email;

public record UserUpdateDTO(
        String name,
        @Email(message = "Email format is invalid")
        String email
) {
}
