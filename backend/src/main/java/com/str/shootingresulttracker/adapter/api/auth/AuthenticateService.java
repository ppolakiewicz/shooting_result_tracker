package com.str.shootingresulttracker.adapter.api.auth;

import com.str.shootingresulttracker.configuration.security.JwtService;
import com.str.shootingresulttracker.domain.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AuthenticateService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterDto registerDto) {
        var newUser = userService.create(
                registerDto.email(),
                passwordEncoder.encode(registerDto.password())
        );

        var jwtToken = jwtService.generateToken(newUser);

        return new AuthenticationResponseDto(jwtToken);
    }

    public AuthenticationResponseDto authenticate(AuthenticateDto authenticateDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateDto.email(),
                        authenticateDto.password()
                )
        );

        var user = userService.findByEmail(authenticateDto.email());
        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponseDto(jwtToken);
    }
}
