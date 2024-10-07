package com.powerkr_teste.powerkr.dto;

import com.powerkr_teste.powerkr.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record RegisterDTO(
        @NotEmpty(message = "O nome é obrigatório")
        @NotBlank
        @Length(min = 2, max = 100, message = "O nome deve ter no minimo 2 caracteres")
        String name,

        @NotEmpty(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        @Length(max = 100, message = "O nome deve ter no minimo 2 caracteres")
        @NotBlank
        String email,

        @NotEmpty(message = "A senha é obrigatória")
        @NotBlank
        @Length(min = 6, max = 100, message = "A senha deve ter no minimo 6 caracteres")
        String password,
        UserRole role
) {}
