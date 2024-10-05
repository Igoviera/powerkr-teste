package com.powerkr_teste.powerkr.controller;

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
    public User createUser(@Valid @RequestBody User user){
        return userService.createUser(user);
    }
    @GetMapping("/{id}")
    public User findById(@Valid @PathVariable("id") Long id){
       return userService.findById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@Valid @PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public void createUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
