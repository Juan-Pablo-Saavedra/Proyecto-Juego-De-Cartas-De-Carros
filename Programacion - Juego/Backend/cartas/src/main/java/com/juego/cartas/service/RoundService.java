package com.juego.cartas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juego.cartas.DTO.RoundDTO;
import com.juego.cartas.DTO.ResponseDTO;
import com.juego.cartas.model.Round;
import com.juego.cartas.repository.IRound;

@Service
public class RoundService {

    @Autowired
    private IRound repo;

    public List<Round> findAll() {
        return repo.findAll();
    }

    public Optional<Round> findById(int id) {
        return repo.findById(id);
    }

    public ResponseDTO deleteRound(int id) {
        if (!repo.existsById(id)) {
            return new ResponseDTO("404", "Ronda no encontrada");
        }
        repo.deleteById(id);
        return new ResponseDTO("200", "Ronda eliminada correctamente");
    }

    public ResponseDTO save(RoundDTO dto) {
        Round r = convertToModel(dto);
        repo.save(r);
        return new ResponseDTO("200", "Ronda creada correctamente");
    }

    public ResponseDTO updateRound(int id, RoundDTO dto) {
        Optional<Round> opt = repo.findById(id);
        if (!opt.isPresent()) {
            return new ResponseDTO("404", "Ronda no encontrada");
        }
        Round r = opt.get();
        r.setRoundNumber(dto.getRoundNumber());
        r.setSelectedAttribute(dto.getSelectedAttribute());
        repo.save(r);
        return new ResponseDTO("200", "Ronda actualizada correctamente");
    }

    public RoundDTO convertToDTO(Round r) {
        return new RoundDTO(
            r.getId_round(),
            r.getRoundNumber(),
            r.getSelectingPlayer().getId_player(),
            r.getSelectedAttribute(),
            r.getWinnerPlayer() != null ? r.getWinnerPlayer().getId_player() : null,
            r.getTimestamp(),
            r.getPlays().stream()
                        .map(p -> p.toDTO())
                        .toList()
        );
    }

    public Round convertToModel(RoundDTO dto) {
        Round r = new Round();
        r.setId_round(dto.getRoundId());
        r.setRoundNumber(dto.getRoundNumber());
        r.setSelectedAttribute(dto.getSelectedAttribute());
        r.setTimestamp(dto.getTimestamp());
        // seleccionar jugador y ganador se ligan en lógica de juego
        return r;
    }
}
