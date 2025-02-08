package com.gestaoesportiva.api.domain.usuario;

import com.gestaoesportiva.api.domain.enumeration.Role;

public record RegistroDTO(String login, String senha, Role role) {
}
