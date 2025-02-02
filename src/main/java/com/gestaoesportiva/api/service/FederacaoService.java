package com.gestaoesportiva.api.service;

import com.gestaoesportiva.api.domain.federacao.Federacao;
import com.gestaoesportiva.api.domain.federacao.DadosAtualizacaoFederacao;
import com.gestaoesportiva.api.domain.federacao.DadosCadastroFederacao;
import com.gestaoesportiva.api.domain.federacao.DadosListagemFederacao;
import com.gestaoesportiva.api.exception.FederacaoNaoEncontradaException;
import com.gestaoesportiva.api.repositories.FederacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FederacaoService {
    @Autowired
    public FederacaoRepository federacaoRepository;

    public FederacaoService(FederacaoRepository federacaoRepository) {
        this.federacaoRepository = federacaoRepository;
    }

    public Federacao criarFederacao(DadosCadastroFederacao dados) {
        Federacao novaFederacao = new Federacao();
        novaFederacao.setNome(dados.nome());
        novaFederacao.setSigla(dados.sigla());
        novaFederacao.setEmail(dados.email());
        novaFederacao.setPresidente(dados.presidente());
        novaFederacao.setCidade(dados.cidade());
        novaFederacao.setUf(dados.uf());
        novaFederacao.setAtivo(true);

        federacaoRepository.save(novaFederacao);

        return novaFederacao;
    }

    public Page<DadosListagemFederacao> listarFederacao(Pageable paginacao) {
        Page<Federacao> federacoes = federacaoRepository.findAll(paginacao);
        return federacoes.map(DadosListagemFederacao::new);
    }

    public Federacao atualizarFederacao(DadosAtualizacaoFederacao dados) {
        Federacao novaFederacao = federacaoRepository.findById(dados.id())
                .orElseThrow(() -> new FederacaoNaoEncontradaException("Federação não encontrada!"));

        novaFederacao.atualizarInformacoes(dados);

        return novaFederacao;
    }

    public void inativarFederacao(Long idFederacao) {
        Federacao federacao = federacaoRepository.findById(idFederacao)
                .orElseThrow(() -> new FederacaoNaoEncontradaException("Federação não encontrada!"));

        federacao.inativarFederacao(idFederacao);
        federacaoRepository.save(federacao);
    }

    public void ativarFederacao(Long idFederacao) {
        Federacao federacao = federacaoRepository.findById(idFederacao)
                .orElseThrow(() -> new FederacaoNaoEncontradaException("Federação não encontrada!"));

        federacao.ativarFederacao(idFederacao);
        federacaoRepository.save(federacao);
    }

}
