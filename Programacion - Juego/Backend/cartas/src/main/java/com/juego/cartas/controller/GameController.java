package com.juego.cartas.controller;

import com.juego.cartas.DTO.CreateGameRequestDTO;
import com.juego.cartas.DTO.CreateGameResponseDTO;
import com.juego.cartas.DTO.GameDTO;
import com.juego.cartas.DTO.PlayRoundRequestDTO;
import com.juego.cartas.DTO.ResponseDTO;
import com.juego.cartas.model.Game;
import com.juego.cartas.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAll() {
        List<GameDTO> dtos = service.findAll()
                                    .stream()
                                    .map(service::convertToDTO)
                                    .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getById(@PathVariable int id) {
        return service.findById(id)
                      .map(service::convertToDTO)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/simple")
    public ResponseEntity<ResponseDTO> createSimple(@RequestBody GameDTO dto) {
        ResponseDTO resp = service.save(dto);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateGameResponseDTO> createAdvanced(
            @RequestBody CreateGameRequestDTO req) {

        Game game = service.createGame(req);
        CreateGameResponseDTO resp =
            new CreateGameResponseDTO("200", "Juego creado", game.getId_game());
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(
            @PathVariable int id,
            @RequestBody GameDTO dto) {

        ResponseDTO resp = service.updateGame(id, dto);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int id) {
        ResponseDTO resp = service.deleteGame(id);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<ResponseDTO> start(@PathVariable int id) {
        ResponseDTO resp = service.startGame(id);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/{id}/rounds")
    public ResponseEntity<ResponseDTO> playRound(
            @PathVariable int id,
            @RequestBody PlayRoundRequestDTO req) {

        ResponseDTO resp = service.playRound(id, req.getPlayerId(), req.getSelectedAttribute());
        return ResponseEntity.ok(resp);
    }
}
