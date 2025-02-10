package com.gestaoesportiva.api.application.dto.jogador;

import com.gestaoesportiva.api.domain.enumeration.Categoria;
import com.gestaoesportiva.api.domain.enumeration.Posicao;

public record DadosAtualizacaoJogador(Long id, String nome, Long timeId, String cpf, String sexo, Categoria categoria, Posicao posicao, Boolean ativo) {
}
