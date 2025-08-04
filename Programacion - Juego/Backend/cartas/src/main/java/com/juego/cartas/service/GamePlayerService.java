package com.juego.cartas.service;

import com.juego.cartas.DTO.CardDTO;
import com.juego.cartas.DTO.GamePlayerDTO;
import com.juego.cartas.model.Card;
import com.juego.cartas.model.GamePlayer;
import com.juego.cartas.repository.IGamePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamePlayerService {
    
    @Autowired
    private IGamePlayer gamePlayerRepository;

    /**
     * Actualiza datos de participación de un jugador en un juego.
     */
    public GamePlayerDTO updatePlayerParticipation(int gameId, GamePlayerDTO dto) {
        GamePlayer gp = gamePlayerRepository
            .findByGameIdAndPlayerId(gameId, dto.getPlayer().getId_player())
            .orElseThrow(() ->
                new EntityNotFoundException("GamePlayer no encontrado")
            );

        gp.setTurnOrder(dto.getTurnOrder());
        gp.setCurrentDeckCount(dto.getCurrentDeckCount());

        if (dto.getHand() != null) {
            List<Card> newDeck = dto.getHand().stream()
                .map(this::convertCardDTOToEntity)
                .collect(Collectors.toList());
            gp.setDeck(newDeck);
        }

        gamePlayerRepository.save(gp);
        return toDTO(gp);
    }

    /**
     * Convierte CardDTO a entidad Card
     */
    private Card convertCardDTOToEntity(Card card) {
        // Como dto.getHand() ya devuelve List<Card>, no necesitamos conversión
        return card;
    }

    /**
     * Convierte GamePlayer a GamePlayerDTO
     */
    private GamePlayerDTO toDTO(GamePlayer gp) {
        List<CardDTO> handDto = gp.getDeck().stream()
            .map(this::convertCardToDTO)
            .collect(Collectors.toList());

        GamePlayerDTO dto = new GamePlayerDTO();
        dto.setId_game_player(gp.getId_game_player());
        dto.setPlayer(gp.getPlayer());
        dto.setTurnOrder(gp.getTurnOrder());
        dto.setCurrentDeckCount(gp.getCurrentDeckCount());
        
        // Convertir las cartas y asignarlas
        List<Card> cards = handDto.stream()
            .map(this::convertCardDTOToCard)
            .collect(Collectors.toList());
        dto.setDeck(cards);
        
        return dto;
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