package com.gestaoesportiva.api.application.controller;

import com.gestaoesportiva.api.application.dto.auth.AuthenticationDTO;
import com.gestaoesportiva.api.application.dto.auth.LoginResponseDTO;
import com.gestaoesportiva.api.application.dto.auth.RegistroDTO;
import com.gestaoesportiva.api.domain.entity.Usuario;
import com.gestaoesportiva.api.infra.security.TokenService;
import com.gestaoesportiva.api.infra.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pelas operações de autenticação de usuários.
 *
 * Esta classe oferece dois endpoints principais:
 * 1. `/login`: Para realizar o login de um usuário, autenticando-o e gerando um token JWT.
 * 2. `/register`: Para registrar um novo usuário, realizando a criação do usuário e armazenando suas credenciais de forma segura.
 */
@RestController
@RequestMapping("auth")
@Tag(name = "Autenticação", description = "Autenticação de Usuários")
public class AuthenticationController {

    /**
     * Gerenciador de autenticação do Spring Security. É utilizado para autenticar o usuário durante o login.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Repositório de usuários, utilizado para verificar e salvar usuários no banco de dados.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Serviço responsável pela geração de tokens JWT.
     */
    @Autowired
    private TokenService tokenService;

    /**
     * Endpoint para realizar o login de um usuário.
     *
     * Este método recebe as credenciais de login enviadas pelo cliente, autentica o usuário utilizando o
     * `AuthenticationManager` e gera um token JWT que é enviado de volta ao cliente.
     *
     * @param dados Contém as credenciais de login (nome de usuário e senha).
     * @return Um `ResponseEntity` contendo o token JWT gerado para o usuário autenticado.
     */
    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO dados) {
        // Criação do objeto de autenticação com login e senha fornecidos
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        // Autentica o usuário utilizando o AuthenticationManager
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // Gera o token JWT para o usuário autenticado
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        // Retorna o token gerado encapsulado em um objeto LoginResponseDTO
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    /**
     * Endpoint para registrar um novo usuário.
     *
     * Este método verifica se o nome de usuário já está registrado. Caso contrário, cria um novo usuário,
     * criptografa sua senha utilizando o algoritmo BCrypt e o salva no banco de dados.
     *
     * @param dados Contém as informações de registro do usuário, incluindo o nome de usuário, senha e papel.
     * @return Um `ResponseEntity` que indica o sucesso ou falha na operação de registro.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistroDTO dados) {
        // Verifica se já existe um usuário com o mesmo nome de usuário
        if (this.usuarioRepository.findByLogin(dados.login()) != null) {
            // Se o usuário já existe, retorna uma resposta de erro (BadRequest)
            return ResponseEntity.badRequest().build();
        }

        // Criptografa a senha fornecida utilizando BCrypt
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());

        // Cria um novo objeto Usuario com os dados fornecidos
        Usuario newUser = new Usuario(dados.login(), encryptedPassword, dados.role());

        // Salva o novo usuário no repositório (banco de dados)
        this.usuarioRepository.save(newUser);

        // Retorna uma resposta de sucesso (OK)
        return ResponseEntity.ok().build();
    }
}
