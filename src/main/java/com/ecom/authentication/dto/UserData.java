package com.ecom.authentication.dto;

import com.ecom.authentication.entity.Role;

import java.util.UUID;

public record UserData(UUID userId,
                       String username,
                       Role role) {
}
