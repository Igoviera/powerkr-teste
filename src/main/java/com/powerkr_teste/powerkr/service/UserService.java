package com.powerkr_teste.powerkr.service;

import com.powerkr_teste.powerkr.dto.ResponseUserDTO;
import com.powerkr_teste.powerkr.dto.UserDTO;
import com.powerkr_teste.powerkr.dto.mapper.UserMapper;
import com.powerkr_teste.powerkr.exceptions.RecordNotFoundException;
import com.powerkr_teste.powerkr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public ResponseUserDTO findById(Long id){
        return userMapper.toResponseDTO(userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
    public ResponseUserDTO updateUser(Long id, UserDTO userDTO){
        return userRepository.findById(id)
                .map(existUser -> {

                    existUser.setName(userDTO.name());
                    existUser.setEmail(userDTO.email());
                    String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
                    existUser.setPassword(encryptedPassword);
                    existUser.setRole(userDTO.role());

                    return userMapper.toResponseDTO(userRepository.save(existUser));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    public void deleteUser(Long id){
       userRepository.delete(userRepository.findById(id)
               .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
