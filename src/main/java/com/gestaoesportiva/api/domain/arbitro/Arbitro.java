package com.gestaoesportiva.api.domain.arbitro;

import com.gestaoesportiva.api.domain.federacao.Federacao;
import com.gestaoesportiva.api.domain.jogador.DadosAtualizacaoJogador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "arbitro")
@Entity(name = "arbitros")
public class Arbitro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToOne
    @JoinColumn(name = "arbitro_id")
    private Arbitro dupla;

    private String email;
    private String telefone;
    private String cpf;
    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "federacao_id")
    private Federacao federacao;
    private Boolean ativo;

    public void atualizar(DadosAtualizacaoArbitro dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if (dados.idade() != null) {
            this.idade = dados.idade();
        }
    }

    public void inativar(Long id) {
        this.ativo = false;
    }

    public void ativar(Long id) {
        this.ativo = true;
    }
}
