package com.gestaoesportiva.api.domain.enumeration;

public enum Role {
    FEDERACAO("federacao"), TIME("time"), JOGADOR("jogador"), ARBITRO("arbitro"), USUARIO("usuario");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
