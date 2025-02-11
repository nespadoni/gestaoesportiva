package com.gestaoesportiva.api.application.dto.jogo;

import com.gestaoesportiva.api.domain.enumeration.Categoria;

import java.util.Date;

public record DadosAtualizacaoJogo(Long id, String nome, Date data, Categoria categoria, Long campeonatoId, Long timeCasaId, Long timeVisitanteId, String local, String cidade, String uf, Integer golsTimeCasa, Integer golsTimeVisitante, Integer penaltisTimeCasa, Integer penaltisTimeVisitante, Boolean finalizado) {
}
