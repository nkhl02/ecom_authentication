package com.ecom.authentication.dto.response;

import java.time.LocalDateTime;

public record LoginResponse (
       String accessToken,
       String tokenType,
       long expiresIn
){ }
