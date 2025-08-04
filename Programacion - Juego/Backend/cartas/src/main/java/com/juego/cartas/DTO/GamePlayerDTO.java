package com.juego.cartas.DTO;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.juego.cartas.model.Card;
import com.juego.cartas.model.Game;
import com.juego.cartas.model.Player;

@Entity
@Table(name = "gameplayer")
public class GamePlayerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game_player")
    private int id_game_player;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "turn_order", nullable = false)
    private int turnOrder;

    @Column(name = "current_deck_count", nullable = false)
    private int currentDeckCount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_game_player_id")
    private List<Card> deck = new ArrayList<>();

    public GamePlayerDTO() {}

    public int getId_game_player() {
        return id_game_player;
    }

    public void setId_game_player(int id_game_player) {
        this.id_game_player = id_game_player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }

    public int getCurrentDeckCount() {
        return currentDeckCount;
    }

    public void setCurrentDeckCount(int currentDeckCount) {
        this.currentDeckCount = currentDeckCount;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
        this.currentDeckCount = deck.size();
    }

    /**
     * Alias para quienes todavía invocan getHand()/setHand().
     */
    @Transient
    public List<Card> getHand() {
        return this.getDeck();
    }

    @Transient
    public void setHand(List<Card> hand) {
        this.setDeck(hand);
    }
}
