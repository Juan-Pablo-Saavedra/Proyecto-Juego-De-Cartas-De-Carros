package com.juego.cartas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.juego.cartas.model.Round;

@Repository
public interface IRound extends JpaRepository<Round, Integer> {
}
