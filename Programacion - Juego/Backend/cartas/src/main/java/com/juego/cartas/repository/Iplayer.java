package com.juego.cartas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.juego.cartas.model.Player;

public interface Iplayer
extends JpaRepository<Player, Integer> {
	
}
