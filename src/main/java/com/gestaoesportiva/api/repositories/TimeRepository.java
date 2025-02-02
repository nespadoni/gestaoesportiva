package com.gestaoesportiva.api.repositories;

import com.gestaoesportiva.api.domain.time.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
}
