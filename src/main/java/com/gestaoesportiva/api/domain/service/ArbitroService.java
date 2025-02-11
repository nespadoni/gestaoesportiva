package com.gestaoesportiva.api.domain.service;

import com.gestaoesportiva.api.domain.entity.Arbitro;
import com.gestaoesportiva.api.application.dto.arbitro.DadosAtualizacaoArbitro;
import com.gestaoesportiva.api.application.dto.arbitro.DadosCadastroArbitro;
import com.gestaoesportiva.api.application.dto.arbitro.DadosListagemArbitro;
import com.gestaoesportiva.api.domain.entity.Federacao;
import com.gestaoesportiva.api.domain.exception.ArbitroNaoEncontradoException;
import com.gestaoesportiva.api.domain.exception.FederacaoNaoEncontradaException;
import com.gestaoesportiva.api.infra.repository.ArbitroRepository;
import com.gestaoesportiva.api.infra.repository.FederacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArbitroService {

    public final ArbitroRepository arbitroRepository;

    public final FederacaoRepository federacaoRepository;

    public ArbitroService(ArbitroRepository arbitroRepository, FederacaoRepository federacaoRepository) {
        this.arbitroRepository = arbitroRepository;
        this.federacaoRepository = federacaoRepository;
    }

    public Arbitro cadastrar(DadosCadastroArbitro dados) {
        Arbitro newArbitro = new Arbitro();

        Arbitro dupla = arbitroRepository.findById(dados.duplaId())
                .orElseThrow(() -> new ArbitroNaoEncontradoException("Arbitro de dupla não encontrado"));


        Federacao federacao = federacaoRepository.findById(dados.federacaoId())
                .orElseThrow(() -> new FederacaoNaoEncontradaException("Federação não encontrada"));
        newArbitro.setNome(dados.nome());
        newArbitro.setDupla(dupla);
        newArbitro.setEmail(dados.email());
        newArbitro.setTelefone(dados.telefone());
        newArbitro.setCpf(dados.cpf());
        newArbitro.setIdade(dados.idade());
        newArbitro.setFederacao(federacao);
        newArbitro.setAtivo(true);

        arbitroRepository.save(newArbitro);
        return newArbitro;
    }

    public Page<DadosListagemArbitro> listar(Pageable paginacao) {
        Page<Arbitro> arbitros = arbitroRepository.findAll(paginacao);
        return arbitros.map(DadosListagemArbitro::new);
    }

    public Arbitro atualizar(DadosAtualizacaoArbitro dados) {
        Arbitro arbitro = arbitroRepository.findById(dados.id())
                .orElseThrow(() -> new ArbitroNaoEncontradoException("Arbitro não encontrado"));

        if (dados.federacaoId() != null) {
            Federacao federacao = federacaoRepository.findById(dados.id())
                    .orElseThrow(() -> new FederacaoNaoEncontradaException("Federação não encontrada"));
            arbitro.setFederacao(federacao);
        }

        if (dados.duplaId() != null) {
            Arbitro dupla = arbitroRepository.findById(dados.duplaId())
                    .orElseThrow(() -> new ArbitroNaoEncontradoException("Arbitro de dupla não encontrado"));
            arbitro.setDupla(dupla);
        }

        arbitro.atualizar(dados);
        return arbitro;
    }

    public void inativar(Long id) {
        Arbitro arbitro = arbitroRepository.findById(id)
                .orElseThrow(() -> new ArbitroNaoEncontradoException("Arbitro não encontrado"));
        arbitro.inativar(id);
        arbitroRepository.save(arbitro);
    }

    public void ativar(Long id) {
        Arbitro arbitro = arbitroRepository.findById(id)
                .orElseThrow(() -> new ArbitroNaoEncontradoException("Arbitro não encontrado"));
        arbitro.ativar(id);
        arbitroRepository.save(arbitro);
    }
}
