package com.gestaoesportiva.api.service;

import com.gestaoesportiva.api.domain.jogador.DadosAtualizacaoJogador;
import com.gestaoesportiva.api.domain.jogador.DadosCadastroJogador;
import com.gestaoesportiva.api.domain.jogador.DadosListagemJogador;
import com.gestaoesportiva.api.domain.jogador.Jogador;
import com.gestaoesportiva.api.domain.time.Time;
import com.gestaoesportiva.api.exception.JogadorNaoEncontradoException;
import com.gestaoesportiva.api.exception.TimeNaoEncontradoException;
import com.gestaoesportiva.api.repositories.JogadorRepository;
import com.gestaoesportiva.api.repositories.TimeRepository;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    @Autowired
    public JogadorRepository jogadorRepository;
    @Autowired
    public TimeRepository timeRepository;

    public Jogador criarJogador(DadosCadastroJogador dados) {
        Jogador newJogador = new Jogador();

        Time time = timeRepository.findById(dados.timeId())
                .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));

        newJogador.setNome(dados.nome());
        newJogador.setTime(time);
        newJogador.setCpf(dados.cpf());
        newJogador.setSexo(dados.sexo());
        newJogador.setCategoria(dados.categoria());
        newJogador.setPosicao(dados.posicao());
        newJogador.setAtivo(true);

        jogadorRepository.save(newJogador);
        return newJogador;
    }

    public Page<DadosListagemJogador> listar(Pageable paginacao) {
        Page<Jogador> jogadores = jogadorRepository.findAll(paginacao);
        return jogadores.map(DadosListagemJogador::new);
    }

    public Jogador atualizar(DadosAtualizacaoJogador dados) {
        Jogador jogador = jogadorRepository.findById(dados.id())
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado!"));

        if (dados.timeId() != null) {
            Time time = timeRepository.findById(dados.timeId())
                    .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
            jogador.setTime(time);
        }

        jogador.atualizarInformacoes(dados);
        return jogador;
    }

    public void inativar(Long id) {
        Jogador jogador = jogadorRepository.findById(id)
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado!"));

        jogador.inativar(id);
        jogadorRepository.save(jogador);
    }

    public void ativar(Long id) {
        Jogador jogador = jogadorRepository.findById(id)
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado!"));

        jogador.ativar(id);
        jogadorRepository.save(jogador);
    }


}
