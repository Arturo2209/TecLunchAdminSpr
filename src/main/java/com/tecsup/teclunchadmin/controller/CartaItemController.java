package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Carta;
import com.tecsup.teclunchadmin.model.CartaItem;
import com.tecsup.teclunchadmin.model.Item;
import com.tecsup.teclunchadmin.service.CartaItemService;
import com.tecsup.teclunchadmin.service.CartaService;
import com.tecsup.teclunchadmin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartaitems")
public class CartaItemController {

    @Autowired
    private CartaItemService cartaItemService;

    @Autowired
    private CartaService cartaService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<CartaItem> getAllCartaItems() {
        return cartaItemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaItem> getCartaItemById(@PathVariable Long id) {
        Optional<CartaItem> cartaItem = cartaItemService.findById(id);
        return cartaItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CartaItem> createCartaItem(@RequestBody Map<String, Long> request) {
        Long cartaId = request.get("carta_id");
        Long itemId = request.get("item_id");

        if (cartaId == null || itemId == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Carta> carta = cartaService.findById(cartaId);
        Optional<Item> item = itemService.findById(itemId);

        if (carta.isPresent() && item.isPresent()) {
            CartaItem cartaItem = new CartaItem(carta.get(), item.get());
            CartaItem createdCartaItem = cartaItemService.save(cartaItem);
            return new ResponseEntity<>(createdCartaItem, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartaItem(@PathVariable Long id) {
        Optional<CartaItem> existingCartaItem = cartaItemService.findById(id);
        if (existingCartaItem.isPresent()) {
            cartaItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
