package com.gestaoesportiva.api.application.dto.auth;

import com.gestaoesportiva.api.domain.enumeration.Role;

public record RegistroDTO(String login, String senha, Role role) {
}
