package com.project.spring_acme_backend.Controllers.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Services.Auth.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") 
public class AuthController {

    private final AuthService auth;

    @PostMapping("login")
    public ResponseEntity<AuthResponse>Login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(auth.login(request));
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> Register(@RequestBody Users request){
        return ResponseEntity.ok(auth.register(request));
    }
    
}
