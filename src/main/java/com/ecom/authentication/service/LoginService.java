package com.ecom.authentication.service;

import com.ecom.authentication.dto.request.LoginRequest;
import com.ecom.authentication.dto.response.LoginResponse;

public interface LoginService {

    public LoginResponse generateToken(LoginRequest loginRequest);

}
