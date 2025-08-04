package com.juego.cartas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_player")
    private int id_player;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public Player() {}

    public Player(int id_player, String name) {
        this.id_player = id_player;
        this.name = name;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
