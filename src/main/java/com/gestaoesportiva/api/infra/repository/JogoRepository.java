package com.gestaoesportiva.api.infra.repository;

import com.gestaoesportiva.api.domain.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
}
