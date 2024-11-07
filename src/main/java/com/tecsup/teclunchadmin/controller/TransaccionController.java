package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Pedido;
import com.tecsup.teclunchadmin.model.Transaccion;
import com.tecsup.teclunchadmin.service.PedidoService;
import com.tecsup.teclunchadmin.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Transaccion> getAllTransacciones() {
        return transaccionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> getTransaccionById(@PathVariable Long id) {
        Optional<Transaccion> transaccion = transaccionService.findById(id);
        return transaccion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transaccion> createTransaccion(@RequestParam Long pedidoId, @RequestBody Transaccion transaccion) {
        Optional<Pedido> pedido = pedidoService.findById(pedidoId);
        if (pedido.isPresent()) {
            transaccion.setPedido(pedido.get());
            transaccion.setFecha(LocalDateTime.now());
            Transaccion createdTransaccion = transaccionService.save(transaccion);
            return new ResponseEntity<>(createdTransaccion, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaccion(@PathVariable Long id) {
        Optional<Transaccion> existingTransaccion = transaccionService.findById(id);
        if (existingTransaccion.isPresent()) {
            transaccionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
