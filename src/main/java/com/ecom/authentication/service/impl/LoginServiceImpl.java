package com.ecom.authentication.service.impl;

import com.ecom.authentication.dto.request.LoginRequest;
import com.ecom.authentication.dto.UserData;
import com.ecom.authentication.dto.response.LoginResponse;
import com.ecom.authentication.service.LoginService;
import com.ecom.authentication.service.UserClient;
import com.ecom.authentication.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserClient usersClient;

    @Autowired
    JWTUtil jwtUtil;

    @Override
    public LoginResponse generateToken(LoginRequest loginRequest) {

        String rawCredentials = loginRequest.username() + ":" + loginRequest.password();
        String encodedCredentials = Base64.getEncoder()
                .encodeToString(rawCredentials.getBytes(StandardCharsets.UTF_8));
        String authorization = "Basic " + encodedCredentials;

        UserData userData = usersClient.getUserData(authorization);
        return jwtUtil.generateToken(userData.username(),userData.role());

    }
}
