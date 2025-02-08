package com.gestaoesportiva.api.infra.security;

import com.gestaoesportiva.api.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de segurança que valida o token JWT nas requisições HTTP.
 *
 * Esse filtro verifica a presença de um token JWT nas requisições, valida o token,
 * recupera as informações do usuário e autentica o usuário no contexto de segurança
 * da aplicação.
 *
 * A classe é um componente do Spring e é executada a cada requisição feita à aplicação.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    /**
     * Serviço responsável por validar o token JWT.
     */
    @Autowired
    TokenService tokenService;

    /**
     * Repositório usado para consultar o usuário pelo login.
     */
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Sobrescrita do método doFilterInternal da classe OncePerRequestFilter.
     * Este método é chamado a cada requisição para validar o token e autenticar o usuário.
     *
     * @param request A requisição HTTP que contém o token.
     * @param response A resposta HTTP que será enviada ao cliente.
     * @param filterChain A cadeia de filtros que permitirá o fluxo da requisição.
     * @throws ServletException Se ocorrer algum erro na execução do filtro.
     * @throws IOException Se ocorrer um erro ao ler ou escrever a resposta.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Recupera o token JWT da requisição
        var token = this.recoverToken(request);

        // Se o token estiver presente, realiza a validação
        if (token != null) {
            // Valida o token e recupera o login do usuário associado
            var login = tokenService.validateToken(token);
            System.out.println("Login do usuário: " + login);

            // Busca o usuário pelo login no banco de dados
            UserDetails usuario = usuarioRepository.findByLogin(login);

            // Se o usuário for encontrado, autentica no contexto de segurança
            if (usuario != null) {
                // Cria um objeto de autenticação com o usuário e suas permissões
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                // Define a autenticação no contexto de segurança do Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Caso o usuário não seja encontrado, loga um erro
                System.out.println("Usuário não encontrado para o login: " + login);
            }
        }

        // Passa a requisição adiante na cadeia de filtros
        filterChain.doFilter(request, response);
    }

    /**
     * Recupera o token JWT do cabeçalho da requisição.
     *
     * @param request A requisição HTTP que pode conter o token no cabeçalho Authorization.
     * @return O token JWT ou null se o cabeçalho não estiver presente ou inválido.
     */
    private String recoverToken(HttpServletRequest request) {
        // Recupera o valor do cabeçalho Authorization
        var authHeader = request.getHeader("Authorization");

        // Se o cabeçalho não estiver presente, retorna null
        if (authHeader == null) return null;

        // Remove o prefixo "Bearer " do cabeçalho e retorna apenas o token
        String token = authHeader.replace("Bearer ", "");

        // Log para verificar o token recuperado (recomendado usar logging em vez de System.out.println em produção)
        System.out.println("Token recuperado: " + token);

        return token;
    }
}
