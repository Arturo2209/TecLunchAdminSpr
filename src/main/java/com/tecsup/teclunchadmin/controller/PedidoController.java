package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Pedido;
import com.tecsup.teclunchadmin.model.Usuario;
import com.tecsup.teclunchadmin.model.Reserva;
import com.tecsup.teclunchadmin.service.PedidoService;
import com.tecsup.teclunchadmin.service.UsuarioService;
import com.tecsup.teclunchadmin.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.findById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Map<String, Object> payload) {
        String idInstitucional = (String) payload.get("usuarioId");
        Optional<Usuario> usuario = usuarioService.findById(idInstitucional);
        Optional<Reserva> reserva = reservaService.findById(Long.valueOf(payload.get("reservaId").toString()));

        if (usuario.isPresent() && reserva.isPresent()) {
            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario.get());
            pedido.setReserva(reserva.get());
            pedido.setFechaPedido(LocalDate.parse((String) payload.get("fechaPedido")));
            pedido.setEstado((String) payload.get("estado"));
            Pedido createdPedido = pedidoService.save(pedido);
            return new ResponseEntity<>(createdPedido, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
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
