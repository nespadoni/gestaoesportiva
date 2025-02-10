package com.gestaoesportiva.api.infra.security;

import com.gestaoesportiva.api.infra.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serviço de autorização que implementa a interface UserDetailsService.
 *
 * O `AuthorizationService` é responsável por carregar os detalhes do usuário
 * a partir de uma fonte de dados (no caso, o repositório de usuários).
 * Essa classe é usada pelo Spring Security para realizar a autenticação de usuários
 * com base no nome de usuário (login).
 */
@Service
public class AuthorizationService implements UserDetailsService {

    /**
     * Repositório de usuários, utilizado para buscar o usuário pelo login.
     */
    @Autowired
    public UsuarioRepository usuarioRepository;

    /**
     * Carrega os detalhes do usuário com base no nome de usuário (login).
     *
     * Este método é utilizado pelo Spring Security para buscar e retornar os
     * detalhes do usuário necessário para autenticação.
     *
     * @param username O nome de usuário (login) que será utilizado para buscar o usuário.
     * @return Os detalhes do usuário encontrados, ou lança uma exceção se não encontrado.
     * @throws UsernameNotFoundException Se o usuário não for encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Log para depuração mostrando o nome de usuário sendo buscado
        System.out.println("Buscando usuário: " + username);

        // Busca o usuário no repositório utilizando o login
        // Caso o usuário não seja encontrado, uma exceção UsernameNotFoundException será lançada automaticamente
        return usuarioRepository.findByLogin(username);
    }
}
