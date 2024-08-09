package com.fiap.hackathon;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class JwtTokenUtil {
    private static String SECRET_KEY = "12345678";

    public static String generateToken(String username) {
        var algoritmo = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withIssuer("API")
                .withSubject(username)
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
    }

    private static Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}