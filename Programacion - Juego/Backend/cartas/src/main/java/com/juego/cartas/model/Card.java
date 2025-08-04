package com.juego.cartas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private int id_card;

    @Column(nullable = false)
    private int number;        // 1–7

    @Column(length = 1, nullable = false)
    private char letter;       // A–H

    @Column(nullable = false)
    private int acceleration;

    @Column(nullable = false)
    private int power;

    @Column(nullable = false)
    private int torque;

    @Column(nullable = false)
    private int weight;

    @Column(nullable = false)
    private int downforce;

    @Column(name = "image_url", length = 200)
    private String imageUrl;

    public Card() {}

    public Card(int id_card, int number, char letter,
                int acceleration, int power, int torque,
                int weight, int downforce, String imageUrl) {
        this.id_card = id_card;
        this.number = number;
        this.letter = letter;
        this.acceleration = acceleration;
        this.power = power;
        this.torque = torque;
        this.weight = weight;
        this.downforce = downforce;
        this.imageUrl = imageUrl;
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
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
