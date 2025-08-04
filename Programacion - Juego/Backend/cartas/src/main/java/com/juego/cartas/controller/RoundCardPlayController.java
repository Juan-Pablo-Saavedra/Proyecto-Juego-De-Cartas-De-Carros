package com.juego.cartas.controller;

import com.juego.cartas.DTO.RoundCardPlayDTO;
import com.juego.cartas.DTO.ResponseDTO;
import com.juego.cartas.model.RoundCardPlay;
import com.juego.cartas.service.RoundCardPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/plays")
public class RoundCardPlayController {

    @Autowired
    private RoundCardPlayService service;

    @GetMapping
    public ResponseEntity<List<RoundCardPlay>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoundCardPlay> getById(@PathVariable int id) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody RoundCardPlayDTO dto) {
        ResponseDTO resp = service.save(dto);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(
            @PathVariable int id,
            @RequestBody RoundCardPlayDTO dto) {
        ResponseDTO resp = service.updatePlay(id, dto);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int id) {
        ResponseDTO resp = service.deletePlay(id);
        return ResponseEntity.ok(resp);
    }
}
