package com.ecom.authentication.controller;
import com.ecom.authentication.dto.request.LoginRequest;
import com.ecom.authentication.dto.response.LoginResponse;
import com.ecom.authentication.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> generateToken(@Valid @RequestBody LoginRequest loginRequest){
           return ResponseEntity.ok(loginService.generateToken(loginRequest));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("Healthy");
    }

}
