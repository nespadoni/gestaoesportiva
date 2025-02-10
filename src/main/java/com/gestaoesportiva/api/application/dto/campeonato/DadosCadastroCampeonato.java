package com.gestaoesportiva.api.application.dto.campeonato;

import java.util.Date;

public record DadosCadastroCampeonato(String titulo, String descricao, Date data, Long idFederacao, String endereco, String cidade, String uf) {
}
