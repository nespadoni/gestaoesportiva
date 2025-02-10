package com.gestaoesportiva.api.domain.service;

import com.gestaoesportiva.api.domain.entity.Campeonato;
import com.gestaoesportiva.api.application.dto.campeonato.DadosAtualizacaoCampeonato;
import com.gestaoesportiva.api.application.dto.campeonato.DadosCadastroCampeonato;
import com.gestaoesportiva.api.application.dto.campeonato.DadosListagemCampeonato;
import com.gestaoesportiva.api.domain.entity.Federacao;
import com.gestaoesportiva.api.domain.exception.CampeonatoNaoEncontradoException;
import com.gestaoesportiva.api.domain.exception.FederacaoNaoEncontradaException;
import com.gestaoesportiva.api.infra.repository.CampeonatoRepository;
import com.gestaoesportiva.api.infra.repository.FederacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CampeonatoService {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private FederacaoRepository federacaoRepository;

    public Campeonato cadastrar(DadosCadastroCampeonato dados) {
        Federacao federacao = federacaoRepository.findById(dados.idFederacao())
                .orElseThrow(() -> new FederacaoNaoEncontradaException("Federação não encontrada"));

        Campeonato campeonato = new Campeonato();
        campeonato.setTitulo(dados.titulo());
        campeonato.setDescricao(dados.descricao());
        campeonato.setData(dados.data());
        campeonato.setFederacao(federacao);
        campeonato.setEndereco(dados.endereco());
        campeonato.setCidade(dados.cidade());
        campeonato.setUf(dados.uf());
        campeonato.setAtivo(true);

        campeonatoRepository.save(campeonato);
        return campeonato;
    }

    public Page<DadosListagemCampeonato> listar(Pageable paginacao) {
        Page<Campeonato> campeonatos = campeonatoRepository.findAll(paginacao);
        return campeonatos.map(DadosListagemCampeonato::new);
    }

    public Campeonato atualizar(DadosAtualizacaoCampeonato dados) {
        Campeonato campeonato = campeonatoRepository.findById(dados.id())
                .orElseThrow(() -> new CampeonatoNaoEncontradoException("Campeonato não encontrado"));

        if (dados.idFederacao() != null) {
            Federacao federacao = federacaoRepository.findById(dados.idFederacao())
                    .orElseThrow(() -> new FederacaoNaoEncontradaException("Federação não encontrada"));
            campeonato.setFederacao(federacao);
        }

        campeonato.setTitulo(dados.titulo());
        campeonato.setDescricao(dados.descricao());
        campeonato.setData(dados.data());
        campeonato.setEndereco(dados.endereco());
        campeonato.setCidade(dados.cidade());
        campeonato.setUf(dados.uf());

        return campeonato;
    }

    public void inativar(Long id) {
        Campeonato campeonato = campeonatoRepository.findById(id)
                .orElseThrow(() -> new CampeonatoNaoEncontradoException("Campeonato não encontrado"));
        campeonato.inativar(id);
        campeonatoRepository.save(campeonato);
    }

    public void ativar(Long id) {
        Campeonato campeonato = campeonatoRepository.findById(id)
                .orElseThrow(() -> new CampeonatoNaoEncontradoException("Campeonato não encontrado"));
        campeonato.ativar(id);
        campeonatoRepository.save(campeonato);
    }
}