package com.gestaoesportiva.api.domain.time;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "times")
@Entity(name = "times")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tecnico;
    private String presidente;
    private String cidade;
    private String uf;

    private Boolean ativo;

    public void atualizarInformacoes(DadosAtualizacaoTime dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.tecnico() != null) {
            this.tecnico = dados.tecnico();
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

    public void inativar(Long id) {
        this.ativo = false;
    }

    public void ativar(Long id) {
        this.ativo = true;
    }
}
