package com.juego.cartas.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public class GameDTO {
    @JsonProperty("id")
    private int gameId;
    private int numberOfPlayers;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private Integer winnerPlayerId;
    private List<PlayerDTO> players;
    private List<RoundDTO> rounds;

    public GameDTO() {}

    public GameDTO(int gameId,
                   int numberOfPlayers,
                   LocalDateTime startTime,
                   LocalDateTime endTime,
                   String status,
                   Integer winnerPlayerId,
                   List<PlayerDTO> players,
                   List<RoundDTO> rounds) {
        this.gameId = gameId;
        this.numberOfPlayers = numberOfPlayers;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.winnerPlayerId = winnerPlayerId;
        this.players = players;
        this.rounds = rounds;
    }

    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWinnerPlayerId() {
        return winnerPlayerId;
    }
    public void setWinnerPlayerId(Integer winnerPlayerId) {
        this.winnerPlayerId = winnerPlayerId;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }
    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<RoundDTO> getRounds() {
        return rounds;
    }
    public void setRounds(List<RoundDTO> rounds) {
        this.rounds = rounds;
    }
}