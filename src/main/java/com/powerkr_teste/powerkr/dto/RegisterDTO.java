package com.powerkr_teste.powerkr.dto;

import com.powerkr_teste.powerkr.enums.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
