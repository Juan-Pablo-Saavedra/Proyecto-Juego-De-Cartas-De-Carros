package com.juego.cartas.DTO;

public class CreateGameResponseDTO extends ResponseDTO {
    private int gameId;

    public CreateGameResponseDTO() {
        super();
    }

    public CreateGameResponseDTO(String code, String message, int gameId) {
        super(code, message);
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
