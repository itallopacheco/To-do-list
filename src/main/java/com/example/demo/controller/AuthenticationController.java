package com.example.demo.controller;

import com.example.demo.domain.enums.UserRole;
import com.example.demo.domain.user.dto.AuthenticationDTO;
import com.example.demo.domain.user.dto.LoginResponseDTO;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserCreationDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO authDTO){
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.username(), authDTO.password());
            var auth = authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserCreationDTO userCreationDTO){
            User newUser = userCreationDTO.userCreationDTOtoUser();

            userRepository.save(newUser);

            return ResponseEntity.ok().build();

    }

}
