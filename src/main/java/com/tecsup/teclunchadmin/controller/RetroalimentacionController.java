package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Item;
import com.tecsup.teclunchadmin.model.Retroalimentacion;
import com.tecsup.teclunchadmin.model.Usuario;
import com.tecsup.teclunchadmin.service.ItemService;
import com.tecsup.teclunchadmin.service.RetroalimentacionService;
import com.tecsup.teclunchadmin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/retroalimentaciones")
public class RetroalimentacionController {

    @Autowired
    private RetroalimentacionService retroalimentacionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Retroalimentacion> getAllRetroalimentaciones() {
        return retroalimentacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Retroalimentacion> getRetroalimentacionById(@PathVariable Long id) {
        Optional<Retroalimentacion> retroalimentacion = retroalimentacionService.findById(id);
        return retroalimentacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Retroalimentacion> createRetroalimentacion(@RequestParam String usuarioId, @RequestParam Long itemId, @RequestBody Retroalimentacion retroalimentacion) {
        Optional<Usuario> usuario = usuarioService.findById(usuarioId);
        Optional<Item> item = itemService.findById(itemId);
        if (usuario.isPresent() && item.isPresent()) {
            retroalimentacion.setUsuario(usuario.get());
            retroalimentacion.setItem(item.get());
            Retroalimentacion createdRetroalimentacion = retroalimentacionService.save(retroalimentacion);
            return new ResponseEntity<>(createdRetroalimentacion, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetroalimentacion(@PathVariable Long id) {
        Optional<Retroalimentacion> existingRetroalimentacion = retroalimentacionService.findById(id);
        if (existingRetroalimentacion.isPresent()) {
            retroalimentacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
