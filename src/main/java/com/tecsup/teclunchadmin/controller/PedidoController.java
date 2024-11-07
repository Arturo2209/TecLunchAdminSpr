package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Pedido;
import com.tecsup.teclunchadmin.model.Usuario;
import com.tecsup.teclunchadmin.service.PedidoService;
import com.tecsup.teclunchadmin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.findById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestParam String usuarioId, @RequestBody Pedido pedido) {
        Optional<Usuario> usuario = usuarioService.findById(usuarioId);
        if (usuario.isPresent()) {
            pedido.setUsuario(usuario.get());
            pedido.setFechaPedido(LocalDate.now());
            Pedido createdPedido = pedidoService.save(pedido);
            return new ResponseEntity<>(createdPedido, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        Optional<Pedido> existingPedido = pedidoService.findById(id);
        if (existingPedido.isPresent()) {
            pedido.setId(id);
            Pedido updatedPedido = pedidoService.save(pedido);
            return ResponseEntity.ok(updatedPedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        Optional<Pedido> existingPedido = pedidoService.findById(id);
        if (existingPedido.isPresent()) {
            pedidoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
