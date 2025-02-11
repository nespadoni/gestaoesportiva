package com.gestaoesportiva.api.application.dto.jogo;

import com.gestaoesportiva.api.domain.entity.Campeonato;
import com.gestaoesportiva.api.domain.entity.Jogo;
import com.gestaoesportiva.api.domain.enumeration.Categoria;

import java.util.Date;

public record DadosListagemJogo(
        Long id, String nome, Date data, Categoria categoria, Campeonato campeonatoId, Long timeCasaId, Long timeVisitanteId, String local, String cidade, String uf, Integer golsTimeCasa, Integer golsTimeVisitante, Integer penaltisTimeCasa, Integer penaltisTimeVisitante, Boolean finalizado
) {
    public DadosListagemJogo(Jogo jogo) {
        this(jogo.getId(), jogo.getNome(), jogo.getData(), jogo.getCategoria(), jogo.getCampeonatoId(), jogo.getTimeCasaId().getId(), jogo.getTimeVisitanteId().getId(), jogo.getLocal(), jogo.getCidade(), jogo.getUf(), jogo.getGolsTimeCasa(), jogo.getGolsTimeVisitante(), jogo.getPenaltisTimeCasa(), jogo.getPenaltisTimeVisitante(), jogo.getFinalizado());
    }
}
