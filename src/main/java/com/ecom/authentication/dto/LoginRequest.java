package com.ecom.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record LoginRequest (@NotBlank String username,
                            @NotBlank String password){
}
