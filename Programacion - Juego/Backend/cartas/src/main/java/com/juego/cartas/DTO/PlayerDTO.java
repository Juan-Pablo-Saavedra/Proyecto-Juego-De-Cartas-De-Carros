package com.juego.cartas.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerDTO {
    @JsonProperty("id")
    private int playerId;
    private String name;

    public PlayerDTO() {}

    public PlayerDTO(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
