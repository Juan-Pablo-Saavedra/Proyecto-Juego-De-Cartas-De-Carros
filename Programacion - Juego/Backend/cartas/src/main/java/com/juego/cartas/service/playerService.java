package com.juego.cartas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juego.cartas.DTO.PlayerDTO;
import com.juego.cartas.DTO.ResponseDTO;
import com.juego.cartas.model.Player;
import com.juego.cartas.repository.IPlayer;

@Service
public class PlayerService {

    @Autowired
    private IPlayer repo;

    public List<Player> findAll() {
        return repo.findAll();
    }

    public Optional<Player> findById(int id) {
        return repo.findById(id);
    }

    public ResponseDTO deletePlayer(int id) {
        if (!repo.existsById(id)) {
            return new ResponseDTO("404", "Jugador no encontrado");
        }
        repo.deleteById(id);
        return new ResponseDTO("200", "Jugador eliminado correctamente");
    }

    public ResponseDTO save(PlayerDTO dto) {
        Player p = convertToModel(dto);
        repo.save(p);
        return new ResponseDTO("200", "Jugador guardado correctamente");
    }

    public ResponseDTO updatePlayer(int id, PlayerDTO dto) {
        Optional<Player> opt = repo.findById(id);
        if (!opt.isPresent()) {
            return new ResponseDTO("404", "Jugador no encontrado");
        }
        Player p = opt.get();
        p.setName(dto.getName());
        repo.save(p);
        return new ResponseDTO("200", "Jugador actualizado correctamente");
    }

    public PlayerDTO convertToDTO(Player p) {
        return new PlayerDTO(p.getId_player(), p.getName());
    }

    public Player convertToModel(PlayerDTO dto) {
        return new Player(dto.getPlayerId(), dto.getName());
    }
}
