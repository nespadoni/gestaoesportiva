package com.gestaoesportiva.api.infra.repository;

import com.gestaoesportiva.api.domain.entity.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbitroRepository extends JpaRepository <Arbitro, Long> {
}
