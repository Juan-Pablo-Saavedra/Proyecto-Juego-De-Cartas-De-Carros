package com.juego.cartas.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardDTO {
    @JsonProperty("id")
    private int cardId;
    private int number;
    private String letter;
    private int acceleration;
    private int power;
    private int torque;
    private int weight;
    private int downforce;
    private String imageUrl;

    public CardDTO() {}

    public CardDTO(int cardId, int number, String letter,
                   int acceleration, int power, int torque,
                   int weight, int downforce, String imageUrl) {
        this.cardId = cardId;
        this.number = number;
        this.letter = letter;
        this.acceleration = acceleration;
        this.power = power;
        this.torque = torque;
        this.weight = weight;
        this.downforce = downforce;
        this.imageUrl = imageUrl;
    }

    public int getCardId() {
        return cardId;
    }
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public String getLetter() {
        return letter;
    }
    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }

    public int getTorque() {
        return torque;
    }
    public void setTorque(int torque) {
        this.torque = torque;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDownforce() {
        return downforce;
    }
    public void setDownforce(int downforce) {
        this.downforce = downforce;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
