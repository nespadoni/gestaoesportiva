package com.gestaoesportiva.api.application.dto.arbitro;

import com.gestaoesportiva.api.domain.entity.Arbitro;
import com.gestaoesportiva.api.domain.entity.Federacao;

public record DadosListagemArbitro(Long id, String nome, Arbitro dupla, String email, String telefone, String cpf, Integer idade, Federacao federacao, Boolean ativo) {
    public DadosListagemArbitro(Arbitro arbitro) {
        this(arbitro.getId(), arbitro.getNome(), arbitro.getDupla(), arbitro.getEmail(), arbitro.getTelefone(), arbitro.getCpf(), arbitro.getIdade(), arbitro.getFederacao(), arbitro.getAtivo());
    }
}
