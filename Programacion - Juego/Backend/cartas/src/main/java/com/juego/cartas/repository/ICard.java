package com.juego.cartas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.juego.cartas.model.Card;

@Repository
public interface ICard extends JpaRepository<Card, Integer> {
}
