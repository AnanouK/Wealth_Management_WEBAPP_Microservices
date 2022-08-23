package com.InvestmentDashboard.UserService.Service;

import com.InvestmentDashboard.UserService.Model.Users;
import com.InvestmentDashboard.UserService.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private UserService underTest;

    @BeforeEach
    void setUp() throws Exception
    {
        underTest = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void shouldAddUserWithoutError() {
        //given
        String username = "jack";
        Users newuser = new Users(1,username,"jack@gmail.com","123");
        //when
        String response = underTest.addUser(newuser);
        //then
        verify(userRepository).save(newuser);
        assertEquals(response,"Création de l'utilisateur avec success");
    }

    @Test
    void tryAddUserWithError() {
        //given
        String username = "jack";
        Users newuser = new Users(1,username,"jack@gmail.com","123");
        Users alreadysaved = new Users(2,username,"jack1@gmail.com","1234");
        given(userRepository.findByUsername(username)).willReturn(alreadysaved);
        //when
        String response = underTest.addUser(newuser);
        //then
        verify(userRepository, never()).save(any());
        assertEquals(response,"Impossible, nom d'utilisateur existant");
    }

    @Test
    void shouldLogin() {
        //given
        String username = "jack";
        String password = "123";
        Users newuser = new Users(1,username,"jack@gmail.com",password);
        given(userRepository.findByUsername(username)).willReturn(newuser);
        //when
        String response = underTest.login(username,password);
        //then
        assertEquals(response,"success");
    }

    @Test
    void shouldNotLoginWrongPassword() {
        //given
        String username = "jack";
        String password = "1234";
        Users newuser = new Users(1,username,"jack@gmail.com","123");
        given(userRepository.findByUsername(username)).willReturn(newuser);
        //when
        String response = underTest.login(username,password);
        //then
        assertEquals(response,"fail");
    }

    @Test
    void shouldNotLoginUsernameNotExist() {
        //given
        String username = "jack";
        String password = "123";
        given(userRepository.findByUsername(username)).willReturn(null);
        //when
        String response = underTest.login(username,password);
        //then
        assertEquals(response,"fail");
    }
}