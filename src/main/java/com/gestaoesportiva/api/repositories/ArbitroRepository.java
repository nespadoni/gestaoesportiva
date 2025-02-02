package com.gestaoesportiva.api.repositories;

import com.gestaoesportiva.api.domain.arbitro.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbitroRepository extends JpaRepository <Arbitro, Long> {
}
