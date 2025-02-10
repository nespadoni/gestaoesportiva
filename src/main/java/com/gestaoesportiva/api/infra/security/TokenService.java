package com.gestaoesportiva.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gestaoesportiva.api.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Serviço responsável pela criação e validação de tokens JWT.
 *
 * O `TokenService` gerencia a criação de tokens JWT baseados nas informações do usuário
 * e a validação de tokens existentes para autenticação de requisições.
 */
@Service
public class TokenService {

    /**
     * A chave secreta usada para assinar e validar os tokens JWT.
     * O valor é injetado de um arquivo de configuração (ex: application.properties).
     */
    @Value("${api.security.token.secret}")
    String secret;

    /**
     * Gera um token JWT para o usuário fornecido.
     *
     * O token é criado com base no login do usuário, e inclui a data de expiração
     * configurada para 2 horas após a geração.
     *
     * @param usuario O usuário para o qual o token será gerado.
     * @return O token JWT gerado.
     * @throws IllegalArgumentException Se o login do usuário for nulo ou vazio.
     * @throws RuntimeException Se ocorrer um erro durante a criação do token.
     */
    public String generateToken(Usuario usuario) {
        // Log para depuração mostrando o login do usuário no momento da criação do token
        System.out.println("Login no momento da geração do token: " + usuario.getLogin());

        // Verifica se o login do usuário é válido
        if (usuario.getLogin() == null || usuario.getLogin().isEmpty()) {
            throw new IllegalArgumentException("O login do usuário não pode ser nulo ou vazio.");
        }

        try {
            // Cria um algoritmo de assinatura HMAC com a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Gera o token com os dados do usuário, incluindo:
            // - Issuer (quem emitiu o token)
            // - Subject (o login do usuário)
            // - Expiração (2 horas após a criação)
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(genExpirationDate())  // Define a data de expiração
                    .sign(algorithm);

            // Log do token gerado (recomendado substituir System.out.println por logging em produção)
            System.out.println("Token gerado: " + token);

            return token;
        } catch (JWTCreationException exception) {
            // Lança uma exceção se ocorrer um erro na criação do token
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }

    /**
     * Valida um token JWT fornecido e retorna o login do usuário associado ao token.
     *
     * @param token O token JWT a ser validado.
     * @return O login do usuário se o token for válido, ou null caso contrário.
     */
    public String validateToken(String token) {
        try {
            // Cria o algoritmo de validação usando a mesma chave secreta usada na criação
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Verifica a validade do token e extrai o login (subject) do token
            String login = JWT.require(algorithm)
                    .withIssuer("auth-api")  // Valida o issuer
                    .build()
                    .verify(token)  // Verifica o token
                    .getSubject();  // Extrai o login do token

            // Se o login estiver vazio ou nulo, o token é considerado inválido
            if (login == null || login.isEmpty()) {
                throw new JWTVerificationException("Token inválido: login vazio.");
            }

            // Log do login extraído do token
            System.out.println("Login extraído do token: " + login);

            return login;
        } catch (JWTVerificationException exception) {
            // Retorna null se o token for inválido ou se ocorrer um erro na validação
            return null;
        }
    }

    /**
     * Gera a data de expiração para o token, que é 2 horas após o momento da criação.
     *
     * @return A data de expiração como um Instant.
     */
    private Instant genExpirationDate() {
        // Calcula a data de expiração (2 horas após o momento atual) com o fuso horário especificado
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }
}
