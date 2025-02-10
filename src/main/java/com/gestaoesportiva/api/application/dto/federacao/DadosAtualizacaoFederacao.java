package com.gestaoesportiva.api.application.dto.federacao;

public record DadosAtualizacaoFederacao(Long id, String nome, String sigla, String email, String presidente, String cidade, String uf) {
}
