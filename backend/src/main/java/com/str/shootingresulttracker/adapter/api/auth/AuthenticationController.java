package com.str.shootingresulttracker.adapter.api.auth;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
class AuthenticationController {

    private final AuthenticateService service;

    @PostMapping("/register")
    public AuthenticationResponseDto register(@RequestBody RegisterDto registerDto) {
        return service.register(registerDto);
    }

    @PostMapping
    public AuthenticationResponseDto authenticate(@RequestBody AuthenticateDto authenticateDto) {
        return service.authenticate(authenticateDto);
    }
}
