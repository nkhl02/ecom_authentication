package com.ecom.authentication.controller;
import com.ecom.authentication.dto.LoginRequest;
import com.ecom.authentication.dto.UserData;
import com.ecom.authentication.service.LoginService;
import com.ecom.authentication.service.UserClient;
import com.ecom.authentication.util.JWTUtil;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> generateToken(@Valid @RequestBody LoginRequest loginRequest){
           return ResponseEntity.ok(loginService.generateToken(loginRequest));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("Healthy");
    }

}
