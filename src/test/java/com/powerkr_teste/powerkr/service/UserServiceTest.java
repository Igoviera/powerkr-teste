package com.powerkr_teste.powerkr.service;

import com.powerkr_teste.powerkr.dto.ResponseUserDTO;
import com.powerkr_teste.powerkr.dto.UserDTO;
import com.powerkr_teste.powerkr.dto.mapper.UserMapper;
import com.powerkr_teste.powerkr.enums.UserRole;
import com.powerkr_teste.powerkr.model.User;
import com.powerkr_teste.powerkr.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    public void deveRetornarUmUsuarioQuandoExistir() {
        LocalDateTime now = LocalDateTime.now();

        User mockUser = new User(1L, "igo", "igo@gmail.com", "123456", now);

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        ResponseUserDTO mockResponseUserDTO = new ResponseUserDTO(1L, "igo", "igo@gmail.com", UserRole.USER);
        when(userMapper.toResponseDTO(mockUser)).thenReturn(mockResponseUserDTO);

        ResponseUserDTO result = userService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("igo", result.name());
        assertEquals("igo@gmail.com", result.email());

        verify(userRepository, times(1)).findById(1L);
        verify(userMapper, times(1)).toResponseDTO(mockUser);
    }


    @Test
    public void deveRemoverUmUsuarioQuandoExistir() {
        User mockUser = new User(1L, "igo", "igo@gmail.com", "123456", LocalDateTime.now());

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(mockUser);
    }

}