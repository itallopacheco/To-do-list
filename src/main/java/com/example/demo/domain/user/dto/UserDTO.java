package com.example.demo.domain.user.dto;

import com.example.demo.domain.user.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private Long id;
    private String name;

    private Date birthDate;

    private String email;

    private String username;


    public UserDTO(Long id, String name, Date birthDate, String email, String username){
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
    }

    public UserDTO(){}

    public UserDTO(@org.jetbrains.annotations.NotNull User user){
        this.id = user.getId();
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
