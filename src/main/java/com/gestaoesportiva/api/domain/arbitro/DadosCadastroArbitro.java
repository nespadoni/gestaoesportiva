package com.gestaoesportiva.api.domain.arbitro;

import com.gestaoesportiva.api.domain.federacao.Federacao;

public record DadosCadastroArbitro(String nome, Long duplaId, String email, String telefone, String cpf, Integer idade, Long federacaoId) {
}
