package com.gestaoesportiva.api.application.dto.jogador;

import com.gestaoesportiva.api.domain.entity.Jogador;
import com.gestaoesportiva.api.domain.enumeration.Categoria;
import com.gestaoesportiva.api.domain.enumeration.Posicao;
import com.gestaoesportiva.api.domain.entity.Time;

public record DadosListagemJogador(Long id, String nome, Time time, String cpf, String sexo, Categoria categoria, Posicao posicao) {
    public DadosListagemJogador(Jogador jogador) {
        this(jogador.getId(), jogador.getNome(), jogador.getTime(), jogador.getCpf(), jogador.getSexo(), jogador.getCategoria(), jogador.getPosicao());
    }
}