package com.gestaoesportiva.api.repositories;

import com.gestaoesportiva.api.domain.federacao.Federacao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface FederacaoRepository extends JpaRepository<Federacao, Long> {
}
