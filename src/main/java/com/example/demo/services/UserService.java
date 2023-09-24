package com.example.demo.services;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserCreationDTO;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserUpdateDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Scanner;

public interface UserService {

    UserDTO save(UserCreationDTO userCreationDTO);

    Page<UserDTO> getAll();

    UserDTO findById(Long id);

    Page<UserDTO> search(String searchTerm, Integer page, Integer size);

    UserDTO findByUsername(String username);

    UserDTO update(Long id,UserUpdateDTO userUpdateDTO);

    void delete(Long id);


}
