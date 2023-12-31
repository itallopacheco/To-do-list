package com.example.demo.services.imp;

import com.example.demo.domain.project.dto.ProjectDTO;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserCreationDTO;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserUpdateDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.exceptions.EntityNotFoundException;
import com.example.demo.services.exceptions.UniqueFieldAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO save(UserCreationDTO userCreationDTO) {
        boolean emailExists = userRepository.existsByEmail(userCreationDTO.email());
        boolean usernameExists = userRepository.existsByUsername(userCreationDTO.username());

        if (emailExists && usernameExists) {
            throw new UniqueFieldAlreadyExists("Email: " + userCreationDTO.email() + " and Username: " + userCreationDTO.username() + " already in use");
        } else if (emailExists) {
            throw new UniqueFieldAlreadyExists("Email: " + userCreationDTO.email() + " already in use");
        } else if (usernameExists) {
            throw new UniqueFieldAlreadyExists("Username: " + userCreationDTO.username() + " already in use");
        }
        User user = userCreationDTO.userCreationDTOtoUser();
        userRepository.save(user);
        return new UserDTO(user);

    }

    @Override
    public Page<UserDTO> getAll() {
        int page = 0;
        int size = 1;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "name"
        );
        PageImpl<User> userPage = new PageImpl<>(userRepository.findAll(),pageRequest,size);
        Page<UserDTO> userDTOS = userPage.map(user -> new UserDTO(user));
        return userDTOS;
    }

    @Override
    public Page<ProjectDTO> getProjects(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) throw new EntityNotFoundException("User not found for id: " + id);

        int page = 0;
        int size = 1;

        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "name"
        );

        Page<ProjectDTO> projectDTOS = userRepository.getProjects(id,pageRequest).map(project -> new ProjectDTO(project));
        return projectDTOS;
    }

    @Override
    public UserDTO findById(Long id) {
        return new UserDTO(userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found for id: "+id)));
    }

    @Override
    public Page<UserDTO> search(String searchTerm, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "name");
        Page<User> userPage = userRepository.search(searchTerm, pageRequest);
        Page<UserDTO> userDTOS = userPage.map(user -> new UserDTO(user));
        return  userDTOS;
    }


    @Override
    public UserDTO update(Long id,UserUpdateDTO userUpdateDTO) {
        Boolean emailExists = userRepository.existsByEmail(userUpdateDTO.email());
        Boolean usernameExists = userRepository.existsByUsername(userUpdateDTO.username());
        if (emailExists && usernameExists) throw new UniqueFieldAlreadyExists("Email: " + userUpdateDTO.email() + " and Username: " + userUpdateDTO.username() + " already in use");
        if (emailExists) throw new UniqueFieldAlreadyExists("Email: " + userUpdateDTO.email() + " already in use");
        if (usernameExists) throw new UniqueFieldAlreadyExists("Username: " + userUpdateDTO.username() + " already in use");

       Optional<User> oldUser = userRepository.findById(id);
       if (oldUser.isPresent()){
            User user = oldUser.get();

            if (userUpdateDTO.name() != null) user.setName(userUpdateDTO.name());
            if (userUpdateDTO.username() != null) user.setUsername(userUpdateDTO.username());
            if (userUpdateDTO.email() != null) user.setEmail(userUpdateDTO.email());

            userRepository.save(user);

           return new UserDTO(user);
       } else {
           throw new EntityNotFoundException("User not found for id: "+id);
       }

    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
        } else {
            throw new EntityNotFoundException("User not found for id: "+id);
        }

    }
}
