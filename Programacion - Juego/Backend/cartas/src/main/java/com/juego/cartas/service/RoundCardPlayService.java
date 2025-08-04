package com.juego.cartas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juego.cartas.DTO.CardDTO;
import com.juego.cartas.DTO.RoundCardPlayDTO;
import com.juego.cartas.DTO.ResponseDTO;
import com.juego.cartas.model.Card;
import com.juego.cartas.model.RoundCardPlay;
import com.juego.cartas.repository.IRoundCardPlay;

@Service
public class RoundCardPlayService {

    @Autowired
    private IRoundCardPlay repo;

    public List<RoundCardPlay> findAll() {
        return repo.findAll();
    }

    public Optional<RoundCardPlay> findById(int id) {
        return repo.findById(id);
    }

    public ResponseDTO deletePlay(int id) {
        if (!repo.existsById(id)) {
            return new ResponseDTO("404", "Jugada no encontrada");
        }
        repo.deleteById(id);
        return new ResponseDTO("200", "Jugada eliminada correctamente");
    }

    public ResponseDTO save(RoundCardPlayDTO dto) {
        RoundCardPlay p = convertToModel(dto);
        repo.save(p);
        return new ResponseDTO("200", "Jugada guardada correctamente");
    }

    public ResponseDTO updatePlay(int id, RoundCardPlayDTO dto) {
        Optional<RoundCardPlay> opt = repo.findById(id);
        if (!opt.isPresent()) {
            return new ResponseDTO("404", "Jugada no encontrada");
        }
        RoundCardPlay p = opt.get();
        p.setAttributeValue(dto.getAttributeValue());
        p.setWinner(dto.isWinner());
        repo.save(p);
        return new ResponseDTO("200", "Jugada actualizada correctamente");
    }

    public RoundCardPlayDTO convertToDTO(RoundCardPlay p) {
        return new RoundCardPlayDTO(
            p.getPlayer().getId_player(),
            convertCardToDTO(p.getCard()),
            p.getAttributeValue(),
            p.isWinner()
        );
    }

    public RoundCardPlay convertToModel(RoundCardPlayDTO dto) {
        RoundCardPlay p = new RoundCardPlay();
        // No seteamos el ID aquí porque se genera automáticamente
        p.setAttributeValue(dto.getAttributeValue());
        p.setWinner(dto.isWinner());
        
        // Convertir CardDTO a Card si es necesario
        if (dto.getCard() != null) {
            p.setCard(convertCardDTOToCard(dto.getCard()));
        }
        
        // asociar round, player y carta en lógica de juego/controllers
        return p;
    }

    /**
     * Convierte Card a CardDTO
     */
    private CardDTO convertCardToDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardId(card.getId_card());
        cardDTO.setNumber(card.getNumber());
        cardDTO.setLetter(String.valueOf(card.getLetter()));
        cardDTO.setAcceleration(card.getAcceleration());
        cardDTO.setPower(card.getPower());
        cardDTO.setTorque(card.getTorque());
        cardDTO.setWeight(card.getWeight());
        cardDTO.setDownforce(card.getDownforce());
        cardDTO.setImageUrl(card.getImageUrl());
        return cardDTO;
    }

    /**
     * Convierte CardDTO a Card
     */
    private Card convertCardDTOToCard(CardDTO cardDTO) {
        Card card = new Card();
        card.setId_card(cardDTO.getCardId());
        card.setNumber(cardDTO.getNumber());
        card.setLetter(cardDTO.getLetter().charAt(0));
        card.setAcceleration(cardDTO.getAcceleration());
        card.setPower(cardDTO.getPower());
        card.setTorque(cardDTO.getTorque());
        card.setWeight(cardDTO.getWeight());
        card.setDownforce(cardDTO.getDownforce());
        card.setImageUrl(cardDTO.getImageUrl());
        return card;
    }
}