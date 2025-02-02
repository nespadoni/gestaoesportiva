package com.gestaoesportiva.api.domain.jogador;

import com.gestaoesportiva.api.domain.enumeration.Categoria;
import com.gestaoesportiva.api.domain.enumeration.Posicao;
import com.gestaoesportiva.api.domain.federacao.DadosAtualizacaoFederacao;
import com.gestaoesportiva.api.domain.time.Time;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "jogador")
@Entity(name = "jogadores")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "times_id")
    private Time time;
    private String cpf;
    private String sexo;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Posicao posicao;

    private Boolean ativo;

    public void atualizarInformacoes(DadosAtualizacaoJogador dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if (dados.sexo() != null) {
            this.sexo = dados.sexo();
        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
        if (dados.posicao() != null) {
            this.posicao = dados.posicao();
        }
    }

    public void inativar(Long id) {
        this.ativo = false;
    }

    public void ativar(Long id) {
        this.ativo = true;
    }
}
