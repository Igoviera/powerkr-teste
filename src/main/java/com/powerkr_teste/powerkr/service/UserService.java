package com.powerkr_teste.powerkr.service;

import com.powerkr_teste.powerkr.exceptions.RecordNotFoundException;
import com.powerkr_teste.powerkr.model.User;
import com.powerkr_teste.powerkr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }
    public User updateUser(Long id, User user){
        User userExiste = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

        userExiste.setName(user.getName());
        userExiste.setEmail(user.getEmail());
        userExiste.setPassword(user.getPassword());

        return userRepository.save(userExiste);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
