package com.gestaoesportiva.api.repositories;

import com.gestaoesportiva.api.domain.campeonato.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository <Campeonato, Long> {
}
