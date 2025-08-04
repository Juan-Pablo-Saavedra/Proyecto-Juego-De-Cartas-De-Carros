package com.juego.cartas.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gameplayer")
public class GamePlayer {

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

    // (Opcional) Si quieres persistir las cartas de cada jugador:
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_game_player_id")
    private List<Card> deck = new ArrayList<>();

    public GamePlayer() {}

    // Constructor completo (ajusta según necesites)
    public GamePlayer(int id_game_player, Game game, Player player,
                      int turnOrder, int currentDeckCount) {
        this.id_game_player = id_game_player;
        this.game = game;
        this.player = player;
        this.turnOrder = turnOrder;
        this.currentDeckCount = currentDeckCount;
    }

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
     * Método que tu servicio invocaba y faltaba.
     * Agrega la carta al mazo y ajusta el contador.
     */
    public void incrementDeck(Card card) {
        this.deck.add(card);
        this.currentDeckCount = this.deck.size();
    }

    public Object getHand() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHand'");
    }
}
