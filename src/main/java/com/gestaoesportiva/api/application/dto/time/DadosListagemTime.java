package com.gestaoesportiva.api.application.dto.time;

import com.gestaoesportiva.api.domain.entity.Time;

public record DadosListagemTime(Long id, String nome, String tecnico, String presidente, String cidade, String uf) {
    public DadosListagemTime(Time time) {
        this(time.getId(), time.getNome(), time.getTecnico(), time.getPresidente(), time.getCidade(), time.getUf());
    }
}
