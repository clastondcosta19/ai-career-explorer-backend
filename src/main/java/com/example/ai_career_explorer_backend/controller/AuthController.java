package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.dto.AuthResponse;
import com.example.ai_career_explorer_backend.dto.LoginRequest;
import com.example.ai_career_explorer_backend.dto.RegisterRequest;
import com.example.ai_career_explorer_backend.entity.User;
import com.example.ai_career_explorer_backend.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody RegisterRequest request) {

        User user = authService.register(request);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        String token = authService.login(request);

        User user = authService.getUserByEmail(request.getEmail());

        AuthResponse response = new AuthResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                token
        );

        return ResponseEntity.ok(response);
    }
}