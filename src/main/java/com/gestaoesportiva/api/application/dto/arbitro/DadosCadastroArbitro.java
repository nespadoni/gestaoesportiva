package com.gestaoesportiva.api.application.dto.arbitro;

public record DadosCadastroArbitro(String nome, Long duplaId, String email, String telefone, String cpf, Integer idade, Long federacaoId) {
}
