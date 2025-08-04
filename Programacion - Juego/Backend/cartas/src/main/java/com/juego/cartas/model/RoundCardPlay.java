package com.juego.cartas.model;

import com.juego.cartas.DTO.RoundCardPlayDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "round_card_play")
public class RoundCardPlay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_play")
    private int id_play;

    @ManyToOne
    @JoinColumn(name = "round_id", nullable = false)
    private Round round;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Column(name = "attribute_value", nullable = false)
    private int attributeValue;

    @Column(name = "is_winner", nullable = false)
    private boolean isWinner;

    public RoundCardPlay() {}

    public RoundCardPlay(int id_play, Round round, Player player, Card card,
                         int attributeValue, boolean isWinner) {
        this.id_play = id_play;
        this.round = round;
        this.player = player;
        this.card = card;
        this.attributeValue = attributeValue;
        this.isWinner = isWinner;
    }

    public int getId_play() {
        return id_play;
    }

    public void setId_play(int id_play) {
        this.id_play = id_play;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(int attributeValue) {
        this.attributeValue = attributeValue;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        this.isWinner = winner;
    }

    public RoundCardPlayDTO toDTO() {
        return new RoundCardPlayDTO(
            this.player.getId_player(),
            this.card == null ? null : new com.juego.cartas.DTO.CardDTO(
                card.getId_card(),
                card.getNumber(),
                String.valueOf(card.getLetter()),
                card.getAcceleration(),
                card.getPower(),
                card.getTorque(),
                card.getWeight(),
                card.getDownforce(),
                card.getImageUrl()
            ),
            this.attributeValue,
            this.isWinner
        );
    }
}
