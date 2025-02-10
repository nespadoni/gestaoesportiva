package com.gestaoesportiva.api.application.dto.arbitro;

public record DadosAtualizacaoArbitro(Long id, String nome, Long duplaId, String email, String telefone, String cpf, Integer idade, Long federacaoId) {
}
