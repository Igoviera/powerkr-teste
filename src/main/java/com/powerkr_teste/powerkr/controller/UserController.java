package com.powerkr_teste.powerkr.controller;

import com.powerkr_teste.powerkr.dto.UserDTO;
import com.powerkr_teste.powerkr.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.SecurityConfig;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/powerkrTeste/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Bucar usuário por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
    })
    @GetMapping("/{id}")
    public UserDTO findById(@Valid @PathVariable("id") Long id){
       return userService.findById(id);
    }

    @Operation(summary = "Atualizar usuário", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
    })
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDTO userDTO){
        return userService.updateUser(id, userDTO);
    }

    @Operation(summary = "Excluir usuário", method = "DELETE")
    @DeleteMapping("/{id}")
    public void createUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
