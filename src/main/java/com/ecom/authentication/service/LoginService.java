package com.ecom.authentication.service;

import com.ecom.authentication.dto.LoginRequest;

public interface LoginService {

    public String generateToken(LoginRequest loginRequest);

}
