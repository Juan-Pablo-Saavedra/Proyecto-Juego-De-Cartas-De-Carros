package com.juego.cartas.DTO;

public class PlayRoundRequestDTO {
    private int playerId;
    private String selectedAttribute;

    public PlayRoundRequestDTO() {}

    public PlayRoundRequestDTO(int playerId, String selectedAttribute) {
        this.playerId = playerId;
        this.selectedAttribute = selectedAttribute;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getSelectedAttribute() {
        return selectedAttribute;
    }

    public void setSelectedAttribute(String selectedAttribute) {
        this.selectedAttribute = selectedAttribute;
    }
}
