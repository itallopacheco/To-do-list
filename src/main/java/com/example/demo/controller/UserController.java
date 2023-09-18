package com.example.demo.controller;

import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserUpdateDTO;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable(value = "id") long id){
       return ResponseEntity.ok(userService.findById(id));

    }

    @PutMapping({"{id}"})
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id")long id ,@RequestBody UserUpdateDTO userUpdateDTO){
        return ResponseEntity.ok(userService.updateUser(id,userUpdateDTO));
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
