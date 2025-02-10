package com.gestaoesportiva.api.application.dto.campeonato;

import java.util.Date;

public record DadosAtualizacaoCampeonato(Long id, String titulo, String descricao, Date data, Long idFederacao, String endereco, String cidade, String uf) {
}
