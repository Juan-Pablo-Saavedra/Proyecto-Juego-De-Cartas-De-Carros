package com.juego.cartas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_departure;

    @ManyToOne
    @JoinColumn(name= "id_player")
    private Player id_player;

    @ManyToOne
    @JoinColumn(name= "id_cards")
    private Cards id_cards;

    @Column(nullable = false)
    private int score;

    public Departure(int id_departure, Player id_player, Cards id_cards, int score) {
        this.id_departure = id_departure;
        this.id_player = id_player;
        this.id_cards = id_cards;
        this.score = score;
    }

    public Player getId_player() {
        return id_player;
    }

    public void setId_player(Player id_player) {
        this.id_player = id_player;
    }

    public Cards getId_cards() {
        return id_cards;
    }

    public void setId_cards(Cards id_cards) {
        this.id_cards = id_cards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}