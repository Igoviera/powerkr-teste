package com.powerkr_teste.powerkr.dto.mapper;

import com.powerkr_teste.powerkr.dto.ResponseUserDTO;
import com.powerkr_teste.powerkr.dto.UserDTO;
import com.powerkr_teste.powerkr.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public ResponseUserDTO toResponseDTO(User user) {
        if (user == null){
            return null;
        }

        return new ResponseUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public UserDTO toDTO(User user){
        if (user == null){
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
    public User toEntity(UserDTO userDTO){
        if (userDTO == null){
            return null;
        }

        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setRole(userDTO.role());

        return user;
    }
}
