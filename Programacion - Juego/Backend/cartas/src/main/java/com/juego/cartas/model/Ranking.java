package com.juego.cartas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ranking;

    @Column(nullable = false)
    private int score;

    public Ranking(int id_ranking, int score) {
        this.id_ranking = id_ranking;
        this.score = score;
    }

    public int getId_ranking() {
        return id_ranking;
    }

    public void setId_ranking(int id_ranking) {
        this.id_ranking = id_ranking;
    }   

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}