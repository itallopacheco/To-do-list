package com.example.demo.domain.user.dto;

import com.example.demo.domain.enums.UserRole;
import com.example.demo.domain.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public record UserCreationDTO(
        String name,
        Date birthDate,
        String email,
        String username,
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
