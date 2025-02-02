package com.gestaoesportiva.api.domain.campeonato;

import com.gestaoesportiva.api.domain.federacao.Federacao;
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
@Table(name = "campeonato")
@Entity(name = "campeonatos")
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "federacao_id")
    private Federacao federacao;

    private String endereco;
    private String cidade;
    private String uf;
    private Boolean ativo;

    public void inativar(Long id) {
        this.ativo = false;
    }

    public void ativar(Long id) {
        this.ativo = true;
    }
}
