package com.gestaoesportiva.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gestaoesportiva.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        System.out.println("Login no momento da geração do token: " + usuario.getLogin());
        if (usuario.getLogin() == null || usuario.getLogin().isEmpty()) {
            throw new IllegalArgumentException("O login do usuário não pode ser nulo ou vazio.");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            System.out.println("Token gerado: " + token);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String login = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
            if (login == null || login.isEmpty()) {
                throw new JWTVerificationException("Token inválido: login vazio.");
            }
            System.out.println("Login extraído do token: " + login);
            return login;
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }
}
