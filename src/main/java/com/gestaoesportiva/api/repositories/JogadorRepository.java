package com.gestaoesportiva.api.repositories;

import com.gestaoesportiva.api.domain.jogador.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
