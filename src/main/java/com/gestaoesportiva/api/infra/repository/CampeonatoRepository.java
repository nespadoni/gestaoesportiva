package com.gestaoesportiva.api.infra.repository;

import com.gestaoesportiva.api.domain.entity.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository <Campeonato, Long> {
}
