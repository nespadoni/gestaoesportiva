package com.gestaoesportiva.api.domain.service;

import com.gestaoesportiva.api.application.dto.jogo.DadosAtualizacaoJogo;
import com.gestaoesportiva.api.application.dto.jogo.DadosCadastroJogo;
import com.gestaoesportiva.api.application.dto.jogo.DadosListagemJogo;
import com.gestaoesportiva.api.domain.entity.Campeonato;
import com.gestaoesportiva.api.domain.entity.Jogo;
import com.gestaoesportiva.api.domain.entity.Time;
import com.gestaoesportiva.api.domain.exception.CampeonatoNaoEncontradoException;
import com.gestaoesportiva.api.domain.exception.JogoNaoEncontradoException;
import com.gestaoesportiva.api.domain.exception.TimeNaoEncontradoException;
import com.gestaoesportiva.api.infra.repository.CampeonatoRepository;
import com.gestaoesportiva.api.infra.repository.JogoRepository;
import com.gestaoesportiva.api.infra.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JogoService {

    private final JogoRepository jogoRepository;
    private final CampeonatoRepository campeonatoRepository;
    private final TimeRepository timeRepository;

    public Jogo criarJogo(DadosCadastroJogo dados) {
        Jogo jogo = new Jogo();

        Campeonato campeonato = campeonatoRepository.findById(dados.campeonatoId())
                .orElseThrow(() -> new CampeonatoNaoEncontradoException("Campeonato não encontrado!"));

        Time timeCasa = timeRepository.findById(dados.timeCasaId())
                .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado!"));

        Time timeVisitante = timeRepository.findById(dados.timeVisitanteId())
                .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado!"));

        jogo.setData(dados.data());
        jogo.setCategoria(dados.categoria());
        jogo.setCampeonatoId(campeonato);
        jogo.setTimeCasaId(timeCasa);
        jogo.setTimeVisitanteId(timeVisitante);
        jogo.setLocal(dados.local());
        jogo.setCidade(dados.cidade());
        jogo.setUf(dados.uf());
        jogo.setAtivo(true);
        jogo.definirNomeJogo();

        jogoRepository.save(jogo);
        return jogo;
    }

    public Page<DadosListagemJogo> listarJogos(Pageable paginacao) {
        Page<Jogo> jogos = jogoRepository.findAll(paginacao);
        return jogos.map(DadosListagemJogo::new);
    }

    public Jogo atualizarJogo(DadosAtualizacaoJogo dados) {
        Jogo jogo = jogoRepository.findById(dados.id())
                .orElseThrow(() -> new JogoNaoEncontradoException("Jogo não encontrado!"));

        jogo.atualizarInformacoes(dados);

        return jogo;
    }

    public void inativar(Long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new JogoNaoEncontradoException("Jogo não encontrado!"));

        jogo.inativar(id);
        jogoRepository.save(jogo);
    }

    public void ativar(Long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new JogoNaoEncontradoException("Jogo não encontrado!"));

        jogo.ativar(id);
        jogoRepository.save(jogo);
    }

    public void finalizar(Long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new JogoNaoEncontradoException("Jogo não encontrado!"));

        jogo.finalizar(id);
        jogoRepository.save(jogo);
    }
}
