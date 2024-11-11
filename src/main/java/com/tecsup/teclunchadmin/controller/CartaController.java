package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Carta;
import com.tecsup.teclunchadmin.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carta")
public class CartaController {

    @Autowired
    private CartaService cartaService;

    @GetMapping
    public List<Carta> getAllCartas() {
        return cartaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carta> getCartaById(@PathVariable Long id) {
        Optional<Carta> carta = cartaService.findById(id);
        return carta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carta> createCarta(@RequestBody Carta carta) {
        Carta createdCarta = cartaService.save(carta);
        return new ResponseEntity<>(createdCarta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carta> updateCarta(@PathVariable Long id, @RequestBody Carta carta) {
        Optional<Carta> existingCarta = cartaService.findById(id);
        if (existingCarta.isPresent()) {
            carta.setId(id);
            Carta updatedCarta = cartaService.save(carta);
            return ResponseEntity.ok(updatedCarta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarta(@PathVariable Long id) {
        Optional<Carta> existingCarta = cartaService.findById(id);
        if (existingCarta.isPresent()) {
            cartaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
