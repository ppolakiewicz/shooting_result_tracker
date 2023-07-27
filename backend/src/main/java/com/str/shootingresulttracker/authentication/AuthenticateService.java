package com.str.shootingresulttracker.authentication;

import com.str.shootingresulttracker.configuration.security.JwtService;
import com.str.shootingresulttracker.usermanagment.UserService;
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
        return userService.create(
                        registerDto.email(),
                        passwordEncoder.encode(registerDto.password()))
                .mapValue(jwtService::generateToken)
                .mapValue(AuthenticationResponseDto::new)
                .orElseThrow(userAlreadyExistsError -> new AuthenticationException(
                        userAlreadyExistsError.getErrorKey(),
                        userAlreadyExistsError.getErrorMessage())
                );
    }

    public AuthenticationResponseDto authenticate(AuthenticateDto authenticateDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateDto.email(),
                        authenticateDto.password()
                )
        );

        return userService.findByUserName(authenticateDto.email())
                .mapValue(jwtService::generateToken)
                .mapValue(AuthenticationResponseDto::new)
                .orElseThrow(userDoNotExistsError -> new AuthenticationException(
                        userDoNotExistsError.getErrorKey(),
                        userDoNotExistsError.getErrorMessage()
                ));
    }
}
