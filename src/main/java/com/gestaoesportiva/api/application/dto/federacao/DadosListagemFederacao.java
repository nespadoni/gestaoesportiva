package com.gestaoesportiva.api.application.dto.federacao;

import com.gestaoesportiva.api.domain.entity.Federacao;

public record DadosListagemFederacao(Long id, String nome, String sigla, String email, String presidente, String cidade, String uf) {
    public DadosListagemFederacao(Federacao federacao) {
        this(federacao.getId(), federacao.getNome(), federacao.getSigla(), federacao.getEmail(), federacao.getPresidente(), federacao.getCidade(), federacao.getUf());
    }
}
