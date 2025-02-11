package com.gestaoesportiva.api.domain.entity;

import com.gestaoesportiva.api.application.dto.jogo.DadosAtualizacaoJogo;
import com.gestaoesportiva.api.domain.enumeration.Categoria;
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
@Entity(name = "jogos")
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Date data;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonatoId;

    @ManyToOne
    @JoinColumn(name = "time_casa_id")
    private Time timeCasaId;

    @ManyToOne
    @JoinColumn(name = "time_visitante_id")
    private Time timeVisitanteId;

    private String local;
    private String cidade;
    private String uf;

    private Integer golsTimeCasa;
    private Integer golsTimeVisitante;
    private Integer penaltisTimeCasa;
    private Integer penaltisTimeVisitante;
    private Boolean finalizado;
    private Boolean ativo;

    public void finalizarJogo(Long id, Integer golsTimeCasa, Integer golsTimeVisitante, Integer penaltisTimeCasa, Integer penaltisTimeVisitante) {
        this.golsTimeCasa = golsTimeCasa;
        this.golsTimeVisitante = golsTimeVisitante;
        this.penaltisTimeCasa = penaltisTimeCasa;
        this.penaltisTimeVisitante = penaltisTimeVisitante;
        this.finalizado = true;
    }

    public void atualizarPlacar(Long id, Integer golsTimeCasa, Integer golsTimeVisitante, Integer penaltisTimeCasa, Integer penaltisTimeVisitante) {
        if (golsTimeCasa != null) {
            this.golsTimeCasa = golsTimeCasa;
        }
        if (golsTimeVisitante != null) {
            this.golsTimeVisitante = golsTimeVisitante;
        }
        if (penaltisTimeCasa != null) {
            this.penaltisTimeCasa = penaltisTimeCasa;
        }
        if (penaltisTimeVisitante != null) {
            this.penaltisTimeVisitante = penaltisTimeVisitante;
        }
    }

    public void definirNomeJogo() {
        if (this.timeCasaId != null && this.timeVisitanteId != null) {
            this.nome = this.timeCasaId.getNome() + " x " + this.timeVisitanteId.getNome();
        } else {
            throw new IllegalArgumentException("Não é possível definir o nome do jogo sem os times definidos");
        }
    }

    public void inativar(Long id) {
        this.ativo = false;
    }

    public void finalizar(Long id) {
        this.finalizado = true;
    }

    public void ativar(Long id) {
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoJogo dados) {
        if (dados.data() != null) {
            this.data = dados.data();
        }
        if (dados.local() != null) {
            this.local = dados.local();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }

        definirNomeJogo();
    }
}


