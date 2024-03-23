package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    public static final int ID = 999;
    public static final String NAME = "User Test";
    public static final String EMAIL = "user@user.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO = "E-mail já cadastrado no sistema";

    //injections mocked

    @InjectMocks //inject a real instance of UserServiceImpl, because we need to access database
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    //bean mocked

    private User user;
    private UserDTO userDTO;
    private Optional<User> userOptional;

    @BeforeEach //before each test
    void setUp() {
        MockitoAnnotations.openMocks(this); //open the mock
        startUser(); //start the user


    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(userOptional);
        User response = service.findById(ID);
        assertNotNull(response); //validate if the response is not null
        Assertions.assertEquals(User.class, response.getClass()); //validate if the response is an instance of User
        Assertions.assertEquals(ID, response.getId()); //validate if the response id is equals to the user id
        assertEquals(NAME, response.getName()); //validate if the response name is equals to the user name
        assertEquals(EMAIL, response.getEmail()); //validate if the response email is equals to the user email

        //it is possible to use Assertions.assertEquals or just assertEquals
        //and Mockito.when or just when -> more actions -> import static org.mockito.Mockito.when;
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        //assertThrows(ObjectNotFoundException.class, () -> userService.findById(ID));
        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, service.findAll().size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenCreateThenReturnAndDataIntegratyViolationException() {
        when(repository.findByEmail(any())).thenReturn(userOptional);

        try {
            userOptional.get().setId(2); //id value changed in order to throw an exception
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenResturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenUpdateTheReturnAndDataIntegratyViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            userOptional.get().setId(2); //id value changed in order to throw an exception
            service.update(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void whenDeleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(userOptional); //check object not found
        doNothing().when(repository).deleteById(anyInt()); //check if the method was called?
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());// check how many times the method was called, it`s expected to be called just once
    }

    @Test
    void whenDeleteWhitAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO)); //throw an exception with the goal of testing the ObjectNotFoundException
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    //LOCAL

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        userOptional = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}