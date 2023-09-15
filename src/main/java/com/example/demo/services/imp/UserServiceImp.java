package com.example.demo.services.imp;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserUpdateDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User u: userList) {
            UserDTO userDTO = new UserDTO(u);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            UserDTO userDTO = new UserDTO(user.get());
            return userDTO;
        } else {
            throw new RuntimeException("user not found for id: " + id);
        }
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findUserByUsername(username);

        if (user != null){
            UserDTO userDTO = new UserDTO(user);
            return userDTO;
        } else {
            throw new RuntimeException("user not found for username: " + username);
        }
    }

    @Override
    public UserDTO updateUser(UserUpdateDTO userUpdateDTO) {
       Optional<User> oldUser = userRepository.findById(userUpdateDTO.id());
       if (oldUser.isPresent()){
            User user = oldUser.get();
            user.setName(userUpdateDTO.name());
            user.setEmail(userUpdateDTO.email());
            userRepository.save(user);

            UserDTO userDTO = new UserDTO(user);
            return userDTO;

       } else {
           throw new RuntimeException("user not found");
       }

    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
        } else {
            throw new RuntimeException("user not found for id: " + id);
        }

    }
}
