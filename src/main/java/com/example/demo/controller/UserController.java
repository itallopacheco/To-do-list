package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserUpdateDTO;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    //TODO: privar para admin
    @GetMapping()
    public ResponseEntity<Page<UserDTO>> getAll(){
        return new ResponseEntity<Page<UserDTO>>(userService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(value = "id") long id){
       UserDTO userDTO = userService.findById(id);
       return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserDTO>> search(
            @RequestParam("searchTerm")String searchTerm,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){

        return new ResponseEntity<Page<UserDTO>>(userService.search(searchTerm,page,size),HttpStatus.OK);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<UserDTO> update(@PathVariable(value = "id")long id ,@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        return ResponseEntity.ok(userService.update(id,userUpdateDTO));
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
