package com.example.demo.services;

import com.example.demo.ApplicationConfigTest;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("UserServiceTest")
public class UserServiceTest extends ApplicationConfigTest {


    @MockBean
    UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    @DisplayName("must create a user")
    public void mustCreateUser(){



        System.out.println("OK");
    }



}
