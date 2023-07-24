package com.str.shootingresulttracker.configuration.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import srt.user.User;

import java.util.Date;

import static java.lang.System.currentTimeMillis;

@Service
public class JwtService {

    private final int tokenExpireTimeInSecond;
    private final JwtAlgorithmProvider jwtAlgorithmProvider;


    public JwtService(
            @Value("${srt.security.jwt.token.expire-time-in-second:3600}") int tokenExpireTimeInSecond,
            JwtAlgorithmProvider jwtAlgorithmProvider) {

        this.tokenExpireTimeInSecond = tokenExpireTimeInSecond;
        this.jwtAlgorithmProvider = jwtAlgorithmProvider;
    }

    public String generateToken(User user) {

        var currentTimeMillis = currentTimeMillis();

        return JWT.create()
                .withSubject(user.email())
                .withClaim("active", user.active())
                .withClaim("role", user.role().toString())
                .withIssuedAt(new Date(currentTimeMillis))
                .withExpiresAt(new Date(currentTimeMillis + tokenExpireTimeInSecond * 1_000L))
                .sign(jwtAlgorithmProvider.provide());
    }

    public String extractUsername(String token) {
        return decodedJWT(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        var username = extractUsername(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(String token) {
        return decodedJWT(token).getExpiresAt();
    }

    private DecodedJWT decodedJWT(String token) {
        return JWT.require(jwtAlgorithmProvider.provide())
                .build()
                .verify(token);
    }
}
