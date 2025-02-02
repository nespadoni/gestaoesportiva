package com.gestaoesportiva.api.domain.jogador;

import com.gestaoesportiva.api.domain.enumeration.Categoria;
import com.gestaoesportiva.api.domain.enumeration.Posicao;
import com.gestaoesportiva.api.domain.time.Time;

public record DadosAtualizacaoJogador(Long id, String nome, Long timeId, String cpf, String sexo, Categoria categoria, Posicao posicao, Boolean ativo) {
}
