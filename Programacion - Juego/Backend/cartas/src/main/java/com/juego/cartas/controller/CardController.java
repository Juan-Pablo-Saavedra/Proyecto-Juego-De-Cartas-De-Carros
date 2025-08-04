package com.juego.cartas.controller;

import com.juego.cartas.DTO.CardDTO;
import com.juego.cartas.DTO.ResponseDTO;
import com.juego.cartas.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService service;

    @GetMapping
    public ResponseEntity<List<CardDTO>> getAll() {
        List<CardDTO> dtos = service.findAll()
            .stream()
            .map(service::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getById(@PathVariable int id) {
        return service.findById(id)
            .map(service::convertToDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody CardDTO dto) {
        ResponseDTO resp = service.save(dto);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(
            @PathVariable int id,
            @RequestBody CardDTO dto) {
        ResponseDTO resp = service.updateCard(id, dto);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int id) {
        ResponseDTO resp = service.deleteCard(id);
        return ResponseEntity.ok(resp);
    }
}
