package com.powerkr_teste.powerkr.service;

import com.powerkr_teste.powerkr.dto.UserDTO;
import com.powerkr_teste.powerkr.dto.mapper.UserMapper;
import com.powerkr_teste.powerkr.model.User;
import com.powerkr_teste.powerkr.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    public void deveRetornarUmUsuario(){
        LocalDateTime now = LocalDateTime.now();

        // Criando um mock de User
        User mockUser = new User(1L,"igo","igo@gmail.com","123456",now);

        // Simulando o comportamento do repositório
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // Simulando o comportamento do UserMapper
        UserDTO mockUserDTO = new UserDTO(1L, "igo", "igo@gmail.com", "123456");
        when(userMapper.toDTO(mockUser)).thenReturn(mockUserDTO); // Simula a conversão

        // Chamando o método que estamos testando
        UserDTO userDTO = userService.findById(1L);

        // Verificando se os valores estão corretos
        assertNotNull(userDTO);
        assertEquals(1L, userDTO.id());
        assertEquals("igo", userDTO.name());
        assertEquals("igo@gmail.com", userDTO.email());
        assertEquals("123456", userDTO.password());
    }

    @Test
    public void deveCriarUmUsuario() {
        LocalDateTime now = LocalDateTime.now();

        // Criando um UserDTO de entrada (request) que será usado no método de criação
        UserDTO userDTO = new UserDTO(null, "igo", "igo@gmail.com", "123456");

        // Criando um User que será salvo no banco de dados
        User userToSave = new User(null, "igo", "igo@gmail.com", "123456", now);

        // Criando o User que será retornado após o salvamento (com o ID gerado pelo banco)
        User savedUser = new User(1L, "igo", "igo@gmail.com", "123456", now);

        // Simulando o comportamento do UserMapper para converter de DTO para entidade
        when(userMapper.toEntity(userDTO)).thenReturn(userToSave);

        // Simulando o comportamento do UserRepository ao salvar o usuário
        when(userRepository.save(userToSave)).thenReturn(savedUser);

        // Simulando o comportamento do UserMapper para converter de entidade para DTO após o salvamento
        UserDTO savedUserDTO = new UserDTO(1L, "igo", "igo@gmail.com", "123456");
        when(userMapper.toDTO(savedUser)).thenReturn(savedUserDTO);

        // Chamando o método de criação do UserService
        UserDTO result = userService.createUser(userDTO);

        // Verificando se os valores retornados estão corretos
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("igo", result.name());
        assertEquals("igo@gmail.com", result.email());
        assertEquals("123456", result.password());

        // Verificando se o método save do repositório foi chamado corretamente
        verify(userRepository, times(1)).save(userToSave);
    }
    @Test
    public void deveAtualizarUmUsuario() {
        LocalDateTime now = LocalDateTime.now();

        // DTO de entrada com os novos dados para o usuário
        UserDTO updateUserDTO = new UserDTO(1L, "igo atualizado", "igo_atualizado@gmail.com", "654321");

        // Usuário existente no banco de dados antes da atualização
        User existingUser = new User(1L, "igo", "igo@gmail.com", "123456", now);

        // Usuário que será salvo após a atualização
        User updatedUser = new User(1L, "igo atualizado", "igo_atualizado@gmail.com", "654321", now);

        // Simulando o comportamento do repositório ao buscar o usuário existente
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        // Simulando o comportamento do UserMapper para mapear o UserDTO atualizado para entidade User
        when(userMapper.toEntity(updateUserDTO)).thenReturn(updatedUser);

        // Simulando o comportamento do repositório ao salvar o usuário atualizado
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        // Simulando o comportamento do UserMapper para converter a entidade atualizada de volta para DTO
        UserDTO updatedUserDTO = new UserDTO(1L, "igo atualizado", "igo_atualizado@gmail.com", "654321");
        when(userMapper.toDTO(updatedUser)).thenReturn(updatedUserDTO);

        // Chamando o método de atualização no UserService
        UserDTO result = userService.updateUser(1L, updateUserDTO);

        // Verificando se os valores retornados estão corretos
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("igo atualizado", result.name());
        assertEquals("igo_atualizado@gmail.com", result.email());
        assertEquals("654321", result.password());

        // Verificando se o método findById foi chamado para buscar o usuário existente
        verify(userRepository, times(1)).findById(1L);

        // Verificando se o método save foi chamado para salvar o usuário atualizado
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    public void deveDeletarUmUsuario() {
        // ID do usuário que será deletado
        Long userId = 1L;

        // Simulando o comportamento do repositório ao buscar o usuário existente
        User existingUser = new User(1L, "igo", "igo@gmail.com", "123456", LocalDateTime.now());
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // Chamando o método de exclusão no UserService
        userService.deleteUser(userId);

        // Verificando se o método deleteById foi chamado no repositório com o ID correto
        verify(userRepository, times(1)).deleteById(userId);

        // Verificando se o método findById foi chamado para verificar a existência do usuário
        verify(userRepository, times(1)).findById(userId);
    }


}