package com.powerkr_teste.powerkr.dto;

import com.powerkr_teste.powerkr.enums.UserRole;

public record ResponseUserDTO(
        Long id,
        String name,
        String email,
        UserRole role
) {}
