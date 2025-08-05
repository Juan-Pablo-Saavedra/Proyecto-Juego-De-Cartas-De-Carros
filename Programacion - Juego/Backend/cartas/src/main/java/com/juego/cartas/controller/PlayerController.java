package com.juego.cartas.controller;

import com.juego.cartas.DTO.PlayerDTO;
import com.juego.cartas.DTO.ResponseDTO;
import com.juego.cartas.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAll() {
        List<PlayerDTO> dtos = service.findAll()
            .stream()
            .map(service::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable int id) {
        return service.findById(id)
            .map(service::convertToDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody PlayerDTO dto) {
        ResponseDTO resp = service.save(dto);
        return ResponseEntity.ok(resp);
    }


}
