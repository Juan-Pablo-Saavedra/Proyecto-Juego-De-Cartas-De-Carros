package com.juego.cartas.model;

import com.juego.cartas.DTO.RoundDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_round")
    private int id_round;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "round_number", nullable = false)
    private int roundNumber;

    @ManyToOne
    @JoinColumn(name = "selecting_player_id", nullable = false)
    private Player selectingPlayer;

    @Column(name = "selected_attribute", length = 20, nullable = false)
    private String selectedAttribute;

    @ManyToOne
    @JoinColumn(name = "winner_player_id")
    private Player winnerPlayer;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private List<RoundCardPlay> plays = new ArrayList<>();

    public Round() {}

    public Round(int id_round, Game game, int roundNumber,
                 Player selectingPlayer, String selectedAttribute,
                 LocalDateTime timestamp) {
        this.id_round = id_round;
        this.game = game;
        this.roundNumber = roundNumber;
        this.selectingPlayer = selectingPlayer;
        this.selectedAttribute = selectedAttribute;
        this.timestamp = timestamp;
    }

    public int getId_round() {
        return id_round;
    }

    public void setId_round(int id_round) {
        this.id_round = id_round;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Player getSelectingPlayer() {
        return selectingPlayer;
    }

    public void setSelectingPlayer(Player selectingPlayer) {
        this.selectingPlayer = selectingPlayer;
    }

    public String getSelectedAttribute() {
        return selectedAttribute;
    }

    public void setSelectedAttribute(String selectedAttribute) {
        this.selectedAttribute = selectedAttribute;
    }

    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setWinnerPlayer(Player winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<RoundCardPlay> getPlays() {
        return plays;
    }

    public void setPlays(List<RoundCardPlay> plays) {
        this.plays = plays;
    }

    public RoundDTO toDTO() {
        List<com.juego.cartas.DTO.RoundCardPlayDTO> dtoPlays = new ArrayList<>();
        for (RoundCardPlay p : plays) {
            dtoPlays.add(p.toDTO());
        }
        return new RoundDTO(
            this.id_round,
            this.roundNumber,
            this.selectingPlayer.getId_player(),
            this.selectedAttribute,
            (this.winnerPlayer != null ? this.winnerPlayer.getId_player() : null),
            this.timestamp,
            dtoPlays
        );
    }
}
