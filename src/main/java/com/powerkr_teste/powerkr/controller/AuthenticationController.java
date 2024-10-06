package com.powerkr_teste.powerkr.controller;

import com.powerkr_teste.powerkr.dto.AuthenticationDTO;
import com.powerkr_teste.powerkr.dto.LoginResponseDTO;
import com.powerkr_teste.powerkr.dto.RegisterDTO;
import com.powerkr_teste.powerkr.model.User;
import com.powerkr_teste.powerkr.repository.UserRepository;
import com.powerkr_teste.powerkr.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/powerkrTeste")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


    @Operation(summary = "Login usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Email não cadastrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro do servidor"),

    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Cadastra usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Email já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro do servidor"),

    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if (this.userRepository.findByEmail(registerDTO.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.name(), registerDTO.email(), encryptedPassword, registerDTO.role());

        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

}
