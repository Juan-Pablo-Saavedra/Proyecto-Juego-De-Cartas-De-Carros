package com.juego.cartas.repository;

import com.juego.cartas.model.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IGamePlayer extends JpaRepository<GamePlayer, Integer> {

    /**
     * Busca todos los GamePlayer de un juego.
     */
    @Query("SELECT gp FROM GamePlayer gp WHERE gp.game.id_game = :gameId")
    List<GamePlayer> findByGameId(@Param("gameId") int gameId);

    /**
     * Busca la participación de un jugador en un juego concreto.
     */
    @Query("SELECT gp FROM GamePlayer gp WHERE gp.game.id_game = :gameId "
         + "AND gp.player.id_player = :playerId")
    Optional<GamePlayer> findByGameIdAndPlayerId(
        @Param("gameId") int gameId,
        @Param("playerId") int playerId
    );
}
