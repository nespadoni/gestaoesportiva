package com.gestaoesportiva.api.domain.campeonato;

import com.gestaoesportiva.api.domain.arbitro.DadosListagemArbitro;
import com.gestaoesportiva.api.domain.federacao.Federacao;

import java.util.Date;

public record DadosListagemCampeonato(Long id, String titulo, String descricao, Date data, Federacao federacao, String endereco, String cidade, String uf, Boolean ativo) {

    public DadosListagemCampeonato(Campeonato campeonato) {
        this(campeonato.getId(), campeonato.getTitulo(), campeonato.getDescricao(), campeonato.getData(), campeonato.getFederacao(), campeonato.getEndereco(), campeonato.getCidade(), campeonato.getUf(), campeonato.getAtivo());
    }
}
