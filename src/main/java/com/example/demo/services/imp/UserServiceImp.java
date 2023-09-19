package com.example.demo.services.imp;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserUpdateDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.exceptions.EntityNotFoundException;
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
        return new UserDTO(userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found for id: "+id)));
    }

    @Override
    public UserDTO findByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id,UserUpdateDTO userUpdateDTO) {
       Optional<User> oldUser = userRepository.findById(id);
       if (oldUser.isPresent()){
            User user = oldUser.get();
            user.setName(userUpdateDTO.name());
            user.setEmail(userUpdateDTO.email());
            userRepository.save(user);

            UserDTO userDTO = new UserDTO(user);
            return userDTO;

       } else {
           throw new EntityNotFoundException("User not found for id: "+id);
       }

    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
        } else {
            throw new EntityNotFoundException("User not found for id: "+id);
        }

    }
}
