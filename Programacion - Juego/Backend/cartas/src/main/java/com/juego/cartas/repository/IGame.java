package com.juego.cartas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.juego.cartas.model.Game;

@Repository
public interface IGame extends JpaRepository<Game, Integer> {
}
