package com.gestaoesportiva.api.service;

import com.gestaoesportiva.api.domain.arbitro.Arbitro;
import com.gestaoesportiva.api.domain.arbitro.DadosAtualizacaoArbitro;
import com.gestaoesportiva.api.domain.arbitro.DadosCadastroArbitro;
import com.gestaoesportiva.api.domain.arbitro.DadosListagemArbitro;
import com.gestaoesportiva.api.domain.federacao.Federacao;
import com.gestaoesportiva.api.exception.ArbitroNaoEncontradoException;
import com.gestaoesportiva.api.exception.FederacaoNaoEncontradaException;
import com.gestaoesportiva.api.repositories.ArbitroRepository;
import com.gestaoesportiva.api.repositories.FederacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArbitroService {

    @Autowired
    public ArbitroRepository arbitroRepository;

    @Autowired
    public FederacaoRepository federacaoRepository;

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
