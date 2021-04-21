package com.easyday.mvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyday.mvp.domain.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
