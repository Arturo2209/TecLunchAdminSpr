package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Categoria;
import com.tecsup.teclunchadmin.model.Item;
import com.tecsup.teclunchadmin.service.CategoriaService;
import com.tecsup.teclunchadmin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.findById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item, @RequestParam Long categoriaId) {
        Optional<Categoria> categoria = categoriaService.findById(categoriaId);
        if (categoria.isPresent()) {
            item.setCategoria(categoria.get());
            Item createdItem = itemService.save(item);
            return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item, @RequestParam Long categoriaId) {
        Optional<Item> existingItem = itemService.findById(id);
        Optional<Categoria> categoria = categoriaService.findById(categoriaId);
        if (existingItem.isPresent() && categoria.isPresent()) {
            item.setId(id);
            item.setCategoria(categoria.get());
            Item updatedItem = itemService.save(item);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        Optional<Item> existingItem = itemService.findById(id);
        if (existingItem.isPresent()) {
            itemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
