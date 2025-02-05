package com.gestaoesportiva.api.service;

import com.gestaoesportiva.api.domain.time.DadosAtualizacaoTime;
import com.gestaoesportiva.api.domain.time.DadosCadastroTime;
import com.gestaoesportiva.api.domain.time.DadosListagemTime;
import com.gestaoesportiva.api.domain.time.Time;
import com.gestaoesportiva.api.exception.TimeNaoEncontradoException;
import com.gestaoesportiva.api.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    @Autowired
    public TimeRepository timeRepository;

    public Time create(DadosCadastroTime dados) {
        Time newTime = new Time();

        newTime.setNome(dados.nome());
        newTime.setTecnico(dados.tecnico());
        newTime.setPresidente(dados.presidente());
        newTime.setCidade(dados.cidade());
        newTime.setUf(dados.uf());
        newTime.setAtivo(true);

        timeRepository.save(newTime);
        return newTime;
    }

    public Page<DadosListagemTime> get(Pageable paginacao) {
        Page<Time> times = timeRepository.findAll(paginacao);
        return times.map(DadosListagemTime::new);
    }

    public Time atualizar(DadosAtualizacaoTime dados) {
        Time time = timeRepository.findById(dados.id())
                .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado!"));

        time.atualizarInformacoes(dados);

        return time;
    }

    public void inativar(Long id) {
        Time time = timeRepository.findById(id)
                .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado!"));

        time.inativar(id);
        timeRepository.save(time);
    }

    public void ativar(Long id) {
        Time time = timeRepository.findById(id)
                .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado!"));

        time.ativar(id);
        timeRepository.save(time);
    }
}