package com.juego.cartas.controller;

import com.juego.cartas.DTO.RoundDTO;
import com.juego.cartas.DTO.ResponseDTO;
import com.juego.cartas.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rounds")
public class RoundController {

    @Autowired
    private RoundService service;

    @GetMapping
    public ResponseEntity<List<RoundDTO>> getAll() {
        List<RoundDTO> dtos = service.findAll()
            .stream()
            .map(service::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoundDTO> getById(@PathVariable int id) {
        return service.findById(id)
            .map(service::convertToDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody RoundDTO dto) {
        ResponseDTO resp = service.save(dto);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(
            @PathVariable int id,
            @RequestBody RoundDTO dto) {
        ResponseDTO resp = service.updateRound(id, dto);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int id) {
        ResponseDTO resp = service.deleteRound(id);
        return ResponseEntity.ok(resp);
    }
}
