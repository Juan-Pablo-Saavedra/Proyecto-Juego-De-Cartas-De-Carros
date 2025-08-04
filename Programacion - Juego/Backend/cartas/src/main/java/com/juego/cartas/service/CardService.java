package com.juego.cartas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juego.cartas.DTO.CardDTO;
import com.juego.cartas.DTO.ResponseDTO;
import com.juego.cartas.model.Card;
import com.juego.cartas.repository.ICard;

@Service
public class CardService {

    @Autowired
    private ICard repo;

    public List<Card> findAll() {
        return repo.findAll();
    }

    public Optional<Card> findById(int id) {
        return repo.findById(id);
    }

    public ResponseDTO deleteCard(int id) {
        if (!repo.existsById(id)) {
            return new ResponseDTO("404", "Carta no encontrada");
        }
        repo.deleteById(id);
        return new ResponseDTO("200", "Carta eliminada correctamente");
    }

    public ResponseDTO save(CardDTO dto) {
        Card c = convertToModel(dto);
        repo.save(c);
        return new ResponseDTO("200", "Carta guardada correctamente");
    }

    public ResponseDTO updateCard(int id, CardDTO dto) {
        Optional<Card> opt = repo.findById(id);
        if (!opt.isPresent()) {
            return new ResponseDTO("404", "Carta no encontrada");
        }
        Card c = opt.get();
        c.setNumber(dto.getNumber());
        c.setLetter(dto.getLetter().charAt(0));
        c.setAcceleration(dto.getAcceleration());
        c.setPower(dto.getPower());
        c.setTorque(dto.getTorque());
        c.setWeight(dto.getWeight());
        c.setDownforce(dto.getDownforce());
        c.setImageUrl(dto.getImageUrl());
        repo.save(c);
        return new ResponseDTO("200", "Carta actualizada correctamente");
    }

    public CardDTO convertToDTO(Card c) {
        return new CardDTO(
            c.getId_card(),
            c.getNumber(),
            String.valueOf(c.getLetter()),
            c.getAcceleration(),
            c.getPower(),
            c.getTorque(),
            c.getWeight(),
            c.getDownforce(),
            c.getImageUrl()
        );
    }

    public Card convertToModel(CardDTO dto) {
        return new Card(
            dto.getCardId(),
            dto.getNumber(),
            dto.getLetter().charAt(0),
            dto.getAcceleration(),
            dto.getPower(),
            dto.getTorque(),
            dto.getWeight(),
            dto.getDownforce(),
            dto.getImageUrl()
        );
    }
}
