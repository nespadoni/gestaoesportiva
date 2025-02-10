package com.gestaoesportiva.api.infra.config;

import com.gestaoesportiva.api.infra.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe de configuração de segurança da aplicação.
 * Define as regras de autenticação, autorização e proteção da API.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Filtro de segurança personalizado para tratamento de autenticação via token JWT.
     */
    @Autowired
    SecurityFilter securityFilter;

    /**
     * Configura a cadeia de filtros de segurança para a aplicação.
     *
     * @param httpSecurity Instância do HttpSecurity usada para configurar as regras de segurança.
     * @return Instância de SecurityFilterChain com as configurações aplicadas.
     * @throws Exception Caso ocorra um erro na configuração de segurança.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Desativa a proteção CSRF, pois a API não mantém estado de sessão.
                .csrf(csrf -> csrf.disable())
                // Define a política de gerenciamento de sessão como STATELESS (sem sessão no servidor).
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configuração de autorização para endpoints específicos.
                .authorizeHttpRequests(authorize -> authorize
                        // Permite acesso irrestrito ao endpoint de login.
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        // Permite acesso irrestrito ao endpoint de registro.
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        // Restringe o cadastro de federações apenas para usuários com a role "FEDERACAO".
                        .requestMatchers(HttpMethod.POST, "/federacoes").hasRole("FEDERACAO")
                        // Libera acesso ao Swagger para documentação da API.
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // Todas as outras requisições exigem autenticação.
                        .anyRequest().authenticated()
                )
                // Adiciona o filtro de segurança antes do filtro padrão de autenticação do Spring Security.
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Configura o gerenciador de autenticação da aplicação.
     *
     * @param authenticationConfiguration Configuração de autenticação do Spring Security.
     * @return Instância de AuthenticationManager para gerenciar autenticação de usuários.
     * @throws Exception Caso ocorra um erro ao obter o gerenciador de autenticação.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configura o encoder de senha para armazenar senhas de forma segura.
     *
     * @return Instância de PasswordEncoder baseada no algoritmo BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
