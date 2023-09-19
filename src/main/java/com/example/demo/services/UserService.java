package com.example.demo.services;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserCreationDTO;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserUpdateDTO;

import java.util.List;
import java.util.Scanner;

public interface UserService {

    UserDTO save(UserCreationDTO userCreationDTO);

    List<UserDTO> getAll();

    UserDTO findById(Long id);

    UserDTO findByUsername(String username);

    UserDTO updateUser(Long id,UserUpdateDTO userUpdateDTO);

    void deleteUser(Long id);


}
