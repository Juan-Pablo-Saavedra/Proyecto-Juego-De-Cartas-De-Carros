package com.juego.cartas.DTO;

public class RoundCardPlayDTO {
    private int playerId;
    private CardDTO card;
    private int attributeValue;
    private boolean winner;

    public RoundCardPlayDTO() {}

    public RoundCardPlayDTO(int playerId, CardDTO card, int attributeValue, boolean winner) {
        this.playerId = playerId;
        this.card = card;
        this.attributeValue = attributeValue;
        this.winner = winner;
    }

    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public CardDTO getCard() {
        return card;
    }
    public void setCard(CardDTO card) {
        this.card = card;
    }

    public int getAttributeValue() {
        return attributeValue;
    }
    public void setAttributeValue(int attributeValue) {
        this.attributeValue = attributeValue;
    }

    public boolean isWinner() {
        return winner;
    }
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
