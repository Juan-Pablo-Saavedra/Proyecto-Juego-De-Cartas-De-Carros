package com.juego.cartas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cards;

    @Column(name = "top_speed")
    private String top_speed;

    @Column(name = "aceleration")
    private int aceleration;

    @Column(name = "torque")
    private int torque;

    @Column(name = "power")
    private int power;

    @Column(name = "weight")
    private int weight;

    public Cards(int id_cards, String top_speed, int aceleration, int torque, int power, int weight) {
    this.id_cards = id_cards;
    this.top_speed = top_speed;
    this.aceleration = aceleration;
    this.torque = torque;
    this.power = power;
    this.weight = weight;
    }

    public int getId_cards() {
        return id_cards;
    }

    public void setId_cards(int id_cards) {
        this.id_cards = id_cards;
    }

    public String getTop_speed() {
        return top_speed;
    }

    public void setTop_speed(String top_speed) {
        this.top_speed = top_speed;
    }

    public int getAceleration() {
        return aceleration;
    }

    public void setAceleration(int aceleration) {
        this.aceleration = aceleration;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    

}