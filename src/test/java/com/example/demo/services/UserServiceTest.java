package com.example.demo.services;

import com.example.demo.domain.enums.UserRole;
import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.dto.ProjectDTO;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserCreationDTO;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserUpdateDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.exceptions.EntityNotFoundException;
import com.example.demo.services.exceptions.UniqueFieldAlreadyExists;
import com.example.demo.services.imp.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Itallo";
    private static final Date BIRTHDATE = new Date(2000, 5, 20);
    private static final String EMAIL = "itallo0@hotmail.com";
    private static final String USERNAME = "itallo";
    private static final String PASSWORD = "senha123";
    private static final UserRole USER_ROLE = UserRole.USER;


    @InjectMocks
    private UserServiceImp userService;
    @Mock
    private UserRepository userRepository;

    private User user;
    private User user2;
    private UserCreationDTO userCreationDTO;
    private UserUpdateDTO userUpdateDTO;
    private UserDTO userDTO;
    private Optional<User> optionalUser;
    private Page<User> pageUsers;
    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        UserDTO response = userService.save(userCreationDTO);

        assertNotNull(response);
        assertEquals(UserDTO.class,response.getClass());
        assertEquals(NAME,response.getName());
        assertEquals(BIRTHDATE,response.getBirthDate());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(USERNAME,response.getUsername());

    }
    @Test
    void whenSaveThenReturnEmailAlreadyExistException() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        try{
            userService.save(userCreationDTO);
        } catch (Exception ex){
            assertEquals(UniqueFieldAlreadyExists.class, ex.getClass());
            assertEquals("Email: " +EMAIL+ " already in use",ex.getMessage());
        }

    }
    @Test
    void whenSaveThenReturnUsernameAlreadyExistException() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        try{
            userService.save(userCreationDTO);
        } catch (Exception ex){
            assertEquals(UniqueFieldAlreadyExists.class, ex.getClass());
            assertEquals("Username: " +USERNAME+ " already in use",ex.getMessage());
        }

    }
    @Test
    void whenSaveThenReturnEmailAndUsernameAlreadyExistException() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        try{
            userService.save(userCreationDTO);
        } catch (Exception ex){
            assertEquals(UniqueFieldAlreadyExists.class, ex.getClass());
            assertEquals("Email: " + EMAIL + " and Username: " + USERNAME + " already in use",ex.getMessage());
        }

    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        Page<UserDTO> response = userService.getAll();

        assertNotNull(response);

        assertEquals(1,response.getTotalElements());
        assertEquals(UserDTO.class,response.getContent().get(0).getClass());
        assertEquals(ID,response.getContent().get(0).getId());

    }

    @Test
    void whenFindAllProjectsThenReturnAnListOfProjects() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);
        when(userRepository.getProjects(anyLong(),any())).thenReturn(new PageImpl<>(List.of(project)));

        Page<ProjectDTO> response = userService.getProjects(ID);

        assertEquals(1,response.getTotalElements());
        assertEquals(ProjectDTO.class,response.getContent().get(0).getClass());
        assertEquals(ID,response.getContent().get(0).getId());
        assertEquals(userDTO,response.getContent().get(0).getOwner());
        assertEquals(1,response.getContent().get(0).getMembers().size());
    }



    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);

        UserDTO response = userService.findById(ID);

        assertNotNull(response);
        assertEquals(UserDTO.class, response.getClass());
        assertEquals(ID,response.getId());

    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(userRepository.findById(anyLong())).thenThrow(new EntityNotFoundException("User not found for id: "));
        try{
            userService.findById(ID);
        }catch (Exception ex){
            assertEquals(EntityNotFoundException.class,ex.getClass());
            assertEquals("User not found for id: ", ex.getMessage());
        }
    }

    @Test
    void searchWhenNoResultsFoundThenReturnEmptyPage() {
        when(userRepository.search(anyString(),any(PageRequest.class))).thenReturn(Page.empty());

        Page<UserDTO> response = userService.search("NonExistentTerm",0,10);
        verify(userRepository, times(1)).
                search("NonExistentTerm",PageRequest.of(0,10, Sort.Direction.ASC,"name"));

        assertTrue(response.isEmpty());
    }

    @Test
    void searchWhenResultFoundThenReturnPageWithResults(){
        when(userRepository.search(anyString(),any(PageRequest.class))).thenReturn(pageUsers);

        Page<UserDTO> response = userService.search("Cai",0,10);
        verify(userRepository,times(1)).search("Cai",PageRequest.of(0,10, Sort.Direction.ASC,"name"));
        assertFalse(response.isEmpty());
        assertEquals(2, response.getTotalElements());
    }


    @Test
    void whenUpdateThenReturnSuccess() {
        when(userRepository.findById(any())).thenReturn(optionalUser);
        when(userRepository.save(any())).thenReturn(user);

        UserDTO response = userService.update(ID,userUpdateDTO);

        assertNotNull(response);
        assertEquals(UserDTO.class,response.getClass());
        assertEquals(NAME,response.getName());
        assertEquals(BIRTHDATE,response.getBirthDate());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(USERNAME,response.getUsername());

    }
    @Test
    void whenUpdateThenReturnEmailAlreadyExistException() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        try{
            userService.update(ID,userUpdateDTO);
        } catch (Exception ex){
            assertEquals(UniqueFieldAlreadyExists.class, ex.getClass());
            assertEquals("Email: " +EMAIL+ " already in use",ex.getMessage());
        }

    }
    @Test
    void whenUpdateThenReturnUsernameAlreadyExistException() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        try{
            userService.update(ID,userUpdateDTO);
        } catch (Exception ex){
            assertEquals(UniqueFieldAlreadyExists.class, ex.getClass());
            assertEquals("Username: " +USERNAME+ " already in use",ex.getMessage());
        }

    }
    @Test
    void whenUpdateThenReturnEmailAndUsernameAlreadyExistException() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        try{
            userService.update(ID,userUpdateDTO);
        } catch (Exception ex){
            assertEquals(UniqueFieldAlreadyExists.class, ex.getClass());
            assertEquals("Email: " + EMAIL + " and Username: " + USERNAME + " already in use",ex.getMessage());
        }

    }
    @Test
    void whenUpdateThenReturnEntityNotFoundException() {
        when(userRepository.findById(anyLong())).thenThrow(new EntityNotFoundException("User not found for id: "));

        try{
            userService.update(ID,userUpdateDTO);
        } catch (Exception ex){
            assertEquals(EntityNotFoundException.class, ex.getClass());
            assertEquals("User not found for id: ", ex.getMessage());
        }

    }

    @Test
    void deleteWithSuccess() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);
        doNothing().when(userRepository).delete(optionalUser.get());
        userService.delete(ID);
        verify(userRepository,times(1)).delete(any());

    }

    @Test
    void whenDeleteReturnEntityNotFoundException() {
        when(userRepository.findById(anyLong())).thenThrow(new EntityNotFoundException("User not found for id: "));
        try{
            userService.delete(ID);
        }catch (Exception ex){
            assertEquals(EntityNotFoundException.class,ex.getClass());
            assertEquals("User not found for id: ", ex.getMessage());
        }

    }


    private void startUser(){

        user = new User(ID,NAME,BIRTHDATE,EMAIL,USERNAME,PASSWORD,USER_ROLE);
        user2 = new User(2L,"Itallo2",new Date(2004,03,9),"itallo2@email.com","itallo2","senha123",UserRole.USER);
        userDTO = new UserDTO(user);
        pageUsers = new PageImpl<>(List.of(user,user2), PageRequest.of(0, 10), 2);
        optionalUser = Optional.of(user);
        userCreationDTO = new UserCreationDTO(NAME,BIRTHDATE,EMAIL,USERNAME,PASSWORD,PASSWORD);
        userUpdateDTO = new UserUpdateDTO(NAME,USERNAME,EMAIL);
        project = new Project(ID,"Project1","Project Desc.",user,List.of(user),null);
    }


}