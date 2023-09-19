package com.example.demo.domain.user.dto;

import com.example.demo.domain.enums.UserRole;
import com.example.demo.domain.user.User;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public record UserCreationDTO(
        @NotEmpty(message = "Name is mandatory")
        String name,
        @Past(message = "Birth date must be in the past")
        @NotNull(message = "Date is mandatory")
        Date birthDate,
        @NotEmpty(message = "Email is mandatory")
        @Email(message = "Email format is invalid")
        String email,

        @NotEmpty(message = "Username is mandatory")
        String username,

        @NotEmpty(message = "Password is mandatory")
        String password,
        String passwordConfirmation
                              )
{

    public User userCreationDTOtoUser(){
        User newUser = new User(
                this.name(),
                this.birthDate(),
                this.email(),
                this.username(),
                new BCryptPasswordEncoder().encode(this.password()),
                UserRole.USER
        );
        return newUser;
    }
}
