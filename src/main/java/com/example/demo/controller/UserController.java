package com.example.demo.controller;

import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserUpdateDTO;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable(value = "id") long id){
       return ResponseEntity.ok(userService.findById(id));

    }

    @GetMapping({"/findByUsername"})
    public ResponseEntity<UserDTO> findUserByName(String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PutMapping({"/update"})
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        return ResponseEntity.ok(userService.updateUser(userUpdateDTO));
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
