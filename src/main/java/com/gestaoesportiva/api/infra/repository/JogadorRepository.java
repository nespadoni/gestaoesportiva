package com.gestaoesportiva.api.infra.repository;

import com.gestaoesportiva.api.domain.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
