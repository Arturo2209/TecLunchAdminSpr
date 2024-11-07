package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Item;
import com.tecsup.teclunchadmin.model.Reserva;
import com.tecsup.teclunchadmin.model.ReservaItem;
import com.tecsup.teclunchadmin.service.ItemService;
import com.tecsup.teclunchadmin.service.ReservaItemService;
import com.tecsup.teclunchadmin.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservaItems")
public class ReservaItemController {

    @Autowired
    private ReservaItemService reservaItemService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<ReservaItem> createReservaItem(@RequestParam Long reservaId, @RequestParam Long itemId, @RequestParam Integer cantidad) {
        Optional<Reserva> reserva = reservaService.findById(reservaId);
        Optional<Item> item = itemService.findById(itemId);
        if (reserva.isPresent() && item.isPresent()) {
            ReservaItem reservaItem = new ReservaItem(reserva.get(), item.get(), cantidad);
            ReservaItem createdReservaItem = reservaItemService.save(reservaItem);
            return new ResponseEntity<>(createdReservaItem, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservaItem(@PathVariable Long id) {
        Optional<ReservaItem> existingReservaItem = reservaItemService.findById(id);
        if (existingReservaItem.isPresent()) {
            reservaItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
