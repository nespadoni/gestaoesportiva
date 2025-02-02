package com.gestaoesportiva.api.domain.time;

public record DadosListagemTime(Long id, String nome, String tecnico, String presidente, String cidade, String uf) {
    public DadosListagemTime(Time time) {
        this(time.getId(), time.getNome(), time.getTecnico(), time.getPresidente(), time.getCidade(), time.getUf());
    }
}
