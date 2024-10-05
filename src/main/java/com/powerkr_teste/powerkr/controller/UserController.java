package com.powerkr_teste.powerkr.controller;

import com.powerkr_teste.powerkr.dto.UserDTO;
import com.powerkr_teste.powerkr.model.User;
import com.powerkr_teste.powerkr.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/powerkrTete/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    @GetMapping("/{id}")
    public UserDTO findById(@Valid @PathVariable("id") Long id){
       return userService.findById(id);
    }
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDTO userDTO){
        return userService.updateUser(id, userDTO);
    }
    @DeleteMapping("/{id}")
    public void createUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
