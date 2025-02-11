package com.gestaoesportiva.api.domain.service;

import com.gestaoesportiva.api.application.dto.time.DadosAtualizacaoTime;
import com.gestaoesportiva.api.application.dto.time.DadosCadastroTime;
import com.gestaoesportiva.api.application.dto.time.DadosListagemTime;
import com.gestaoesportiva.api.domain.entity.Time;
import com.gestaoesportiva.api.domain.exception.TimeNaoEncontradoException;
import com.gestaoesportiva.api.infra.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    public final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

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