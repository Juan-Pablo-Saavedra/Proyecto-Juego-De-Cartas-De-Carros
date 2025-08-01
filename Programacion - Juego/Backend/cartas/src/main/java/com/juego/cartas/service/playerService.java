package com.juego.cartas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juego.cartas.repository.Iplayer;

@Service
public class playerService {

    @Autowired 
    private Iplayer playerRepository;

    public List<Player> findAllPlayers() {
        return playerData.findAll();
    }

    public Optional<Player> findById
    

}
