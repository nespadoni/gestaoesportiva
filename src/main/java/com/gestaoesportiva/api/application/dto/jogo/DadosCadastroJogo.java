package com.gestaoesportiva.api.application.dto.jogo;

import com.gestaoesportiva.api.domain.entity.Campeonato;
import com.gestaoesportiva.api.domain.entity.Time;
import com.gestaoesportiva.api.domain.enumeration.Categoria;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DadosCadastroJogo(@NotBlank Date data,
                                @NotBlank Categoria categoria,
                                @NotBlank Long campeonatoId,
                                @NotBlank Long timeCasaId,
                                @NotBlank Long timeVisitanteId,
                                @NotBlank String local,
                                @NotBlank String cidade,
                                @NotBlank String uf
) {
}
