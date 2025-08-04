package com.juego.cartas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.juego.cartas.model.RoundCardPlay;

@Repository
public interface IRoundCardPlay extends JpaRepository<RoundCardPlay, Integer> {
}
