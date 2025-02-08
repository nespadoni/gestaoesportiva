package com.gestaoesportiva.api.controller;

import com.gestaoesportiva.api.domain.usuario.AuthenticationDTO;
import com.gestaoesportiva.api.domain.usuario.LoginResponseDTO;
import com.gestaoesportiva.api.domain.usuario.RegistroDTO;
import com.gestaoesportiva.api.domain.usuario.Usuario;
import com.gestaoesportiva.api.infra.security.TokenService;
import com.gestaoesportiva.api.repositories.UsuarioRepository;
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

@RestController
@RequestMapping("auth")
@Tag(name = "Autenticação", description = "Autenticação de Usuários")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO dados) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistroDTO dados) {
        if (this.usuarioRepository.findByLogin(dados.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario newUser = new Usuario(dados.login(), encryptedPassword, dados.role());

        this.usuarioRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
