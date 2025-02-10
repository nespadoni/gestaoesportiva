package com.gestaoesportiva.api.infra.repository;

import com.gestaoesportiva.api.domain.entity.Federacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FederacaoRepository extends JpaRepository<Federacao, Long> {
}
