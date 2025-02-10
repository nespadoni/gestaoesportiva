package com.gestaoesportiva.api.domain.entity;

import com.gestaoesportiva.api.application.dto.federacao.DadosAtualizacaoFederacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "federacao")
@Entity(name = "federacoes")
public class Federacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sigla;
    private String email;
    private String presidente;
    private String cidade;
    private String uf;
    private Boolean ativo;

    public void atualizarInformacoes(DadosAtualizacaoFederacao dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.sigla() != null) {
            this.sigla = dados.sigla();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.presidente() != null) {
            this.presidente = dados.presidente();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
    }

    public void inativarFederacao(Long id) {
        this.ativo = false;
    }

    public void ativarFederacao(Long id) {
        this.ativo = true;
    }
}
