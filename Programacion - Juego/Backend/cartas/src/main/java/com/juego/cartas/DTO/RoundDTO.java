package com.juego.cartas.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public class RoundDTO {
    @JsonProperty("id")
    private int roundId;
    private int roundNumber;
    private int selectingPlayerId;
    private String selectedAttribute;
    private Integer winnerPlayerId;
    private LocalDateTime timestamp;
    private List<RoundCardPlayDTO> plays;

    public RoundDTO() {}

    public RoundDTO(int roundId, int roundNumber, int selectingPlayerId,
                    String selectedAttribute, Integer winnerPlayerId,
                    LocalDateTime timestamp, List<RoundCardPlayDTO> plays) {
        this.roundId = roundId;
        this.roundNumber = roundNumber;
        this.selectingPlayerId = selectingPlayerId;
        this.selectedAttribute = selectedAttribute;
        this.winnerPlayerId = winnerPlayerId;
        this.timestamp = timestamp;
        this.plays = plays;
    }

    public int getRoundId() {
        return roundId;
    }
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getSelectingPlayerId() {
        return selectingPlayerId;
    }
    public void setSelectingPlayerId(int selectingPlayerId) {
        this.selectingPlayerId = selectingPlayerId;
    }

    public String getSelectedAttribute() {
        return selectedAttribute;
    }
    public void setSelectedAttribute(String selectedAttribute) {
        this.selectedAttribute = selectedAttribute;
    }

    public Integer getWinnerPlayerId() {
        return winnerPlayerId;
    }
    public void setWinnerPlayerId(Integer winnerPlayerId) {
        this.winnerPlayerId = winnerPlayerId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<RoundCardPlayDTO> getPlays() {
        return plays;
    }
    public void setPlays(List<RoundCardPlayDTO> plays) {
        this.plays = plays;
    }
}
