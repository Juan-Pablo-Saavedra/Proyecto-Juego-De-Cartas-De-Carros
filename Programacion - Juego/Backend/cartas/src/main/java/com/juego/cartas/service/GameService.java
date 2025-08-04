package com.juego.cartas.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juego.cartas.DTO.*;
import com.juego.cartas.model.*;
import com.juego.cartas.repository.*;

@Service
public class GameService {

    @Autowired
    private IGame gameRepo;

    @Autowired
    private IPlayer playerRepo;

    @Autowired
    private ICard cardRepo;

    @Autowired
    private IGamePlayer gamePlayerRepo;

    public List<Game> findAll() {
        return gameRepo.findAll();
    }

    public Optional<Game> findById(int id) {
        return gameRepo.findById(id);
    }

    public ResponseDTO deleteGame(int id) {
        if (!gameRepo.existsById(id)) {
            return new ResponseDTO("404", "Juego no encontrado");
        }
        gameRepo.deleteById(id);
        return new ResponseDTO("200", "Juego eliminado correctamente");
    }

    public ResponseDTO save(GameDTO dto) {
        Game g = convertToModel(dto);
        g.setStatus("CREADO");
        g.setStartTime(LocalDateTime.now());
        gameRepo.save(g);

        int order = 1;
        for (PlayerDTO pd : dto.getPlayers()) {
            Player pl = playerRepo.findById(pd.getPlayerId())
                                  .orElseThrow(() -> new RuntimeException("Jugador inexistente"));
            GamePlayer gp = new GamePlayer();
            gp.setGame(g);
            gp.setPlayer(pl);
            gp.setTurnOrder(order++);
            gp.setCurrentDeckCount(0);
            gamePlayerRepo.save(gp);
        }

        return new ResponseDTO("200", "Juego guardado correctamente");
    }

    public ResponseDTO updateGame(int id, GameDTO dto) {
        Optional<Game> opt = gameRepo.findById(id);
        if (!opt.isPresent()) {
            return new ResponseDTO("404", "Juego no encontrado");
        }
        Game g = opt.get();
        g.setNumberOfPlayers(dto.getNumberOfPlayers());
        gameRepo.save(g);
        return new ResponseDTO("200", "Juego actualizado correctamente");
    }

    // ✅ Nuevo método para CreateGameRequestDTO
    public Game createGame(CreateGameRequestDTO req) {
        Game g = new Game();
        g.setNumberOfPlayers(req.getNumberOfPlayers());
        g.setStatus("CREADO");
        g.setStartTime(LocalDateTime.now());
        gameRepo.save(g);

        int order = 1;
        for (Integer playerId : req.getPlayerIds()) {
            Player pl = playerRepo.findById(playerId)
                                  .orElseThrow(() -> new RuntimeException("Jugador inexistente"));
            GamePlayer gp = new GamePlayer();
            gp.setGame(g);
            gp.setPlayer(pl);
            gp.setTurnOrder(order++);
            gp.setCurrentDeckCount(0);
            gamePlayerRepo.save(gp);
        }

        return g;
    }

    public ResponseDTO startGame(int gameId) {
        Optional<Game> opt = gameRepo.findById(gameId);
        if (!opt.isPresent()) {
            return new ResponseDTO("404", "Juego no encontrado");
        }
        Game g = opt.get();
        List<GamePlayer> playersInGame = gamePlayerRepo.findByGameId(gameId);

        List<Card> deck = cardRepo.findAll();
        Collections.shuffle(deck);
        int pCount = playersInGame.size();
        for (int i = 0; i < deck.size(); i++) {
            GamePlayer gp = playersInGame.get(i % pCount);
            gp.incrementDeck(deck.get(i));
            gamePlayerRepo.save(gp);
        }

        g.setStatus("EN_PROGRESO");
        g.setStartTime(LocalDateTime.now());
        gameRepo.save(g);

        return new ResponseDTO("200", "Juego iniciado correctamente");
    }

    public ResponseDTO playRound(int gameId, int selectingPlayerId, String attribute) {
        // Lógica pendiente: comparar cartas, asignar ganador provisional
        return new ResponseDTO("200", "Ronda jugada correctamente");
    }

    public GameDTO convertToDTO(Game g) {
        return new GameDTO(
            g.getId_game(),
            g.getNumberOfPlayers(),
            g.getStartTime(),
            g.getEndTime(),
            g.getStatus(),
            g.getWinnerPlayerId(),
            g.getPlayers().stream()
                         .map(gp -> new PlayerDTO(
                             gp.getPlayer().getId_player(),
                             gp.getPlayer().getName()))
                         .toList(),
            g.getRounds().stream()
                         .map(r -> r.toDTO())
                         .toList()
        );
    }

    public Game convertToModel(GameDTO dto) {
        return new Game(
            dto.getGameId(),
            dto.getNumberOfPlayers(),
            dto.getStartTime()
        );
    }
}
