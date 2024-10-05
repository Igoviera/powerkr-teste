package com.powerkr_teste.powerkr.service;

import com.powerkr_teste.powerkr.dto.UserDTO;
import com.powerkr_teste.powerkr.dto.mapper.UserMapper;
import com.powerkr_teste.powerkr.exceptions.RecordNotFoundException;
import com.powerkr_teste.powerkr.model.User;
import com.powerkr_teste.powerkr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO){
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }
    public UserDTO findById(Long id){
        return userMapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
    public UserDTO updateUser(Long id, UserDTO userDTO){
        return userRepository.findById(id)
                .map(existUser -> {
                    existUser.setName(userDTO.name());
                    existUser.setEmail(userDTO.email());
                    existUser.setPassword(userDTO.password());

                    return userMapper.toDTO(userRepository.save(existUser));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    public void deleteUser(Long id){
       User user = userRepository.findById(id)
               .orElseThrow(() -> new RecordNotFoundException(id));
       userRepository.deleteById(id);
    }
}
