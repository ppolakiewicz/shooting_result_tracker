package com.str.shootingresulttracker.configuration.security;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class JwtAlgorithmProvider {

    @Value("${srt.security.jwt.token.algorithm.secret}")
    private String algorithmSecret;

    public Algorithm provide(){
        return Algorithm.HMAC512(algorithmSecret);
    }
}
