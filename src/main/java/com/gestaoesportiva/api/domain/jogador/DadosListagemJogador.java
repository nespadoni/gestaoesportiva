package com.gestaoesportiva.api.domain.jogador;

import com.gestaoesportiva.api.domain.enumeration.Categoria;
import com.gestaoesportiva.api.domain.enumeration.Posicao;
import com.gestaoesportiva.api.domain.federacao.Federacao;
import com.gestaoesportiva.api.domain.time.Time;

public record DadosListagemJogador(Long id, String nome, Time time, String cpf, String sexo, Categoria categoria, Posicao posicao) {
    public DadosListagemJogador(Jogador jogador) {
        this(jogador.getId(), jogador.getNome(), jogador.getTime(), jogador.getCpf(), jogador.getSexo(), jogador.getCategoria(), jogador.getPosicao());
    }
}