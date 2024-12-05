package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Transaccion;
import com.tecsup.teclunchadmin.model.Pedido;
import com.tecsup.teclunchadmin.service.TransaccionService;
import com.tecsup.teclunchadmin.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private PedidoService pedidoService;

    // Obtener todas las transacciones
    @GetMapping
    public ResponseEntity<List<Transaccion>> getAllTransacciones() {
        return ResponseEntity.ok(transaccionService.findAll());
    }

    // Crear una transacción
    @PostMapping
    public ResponseEntity<Transaccion> createTransaccion(@RequestBody Map<String, Object> payload) {
        Long pedidoId = Long.valueOf(payload.get("pedidoId").toString());
        String metodoPago = (String) payload.get("metodoPago");
        String estado = (String) payload.get("estado");
        Double monto = Double.valueOf(payload.get("monto").toString());

        // Buscar pedido
        Optional<Pedido> pedido = pedidoService.findById(pedidoId);

        if (pedido.isPresent()) {
            Transaccion transaccion = new Transaccion();
            transaccion.setPedido(pedido.get());
            transaccion.setMetodoPago(metodoPago);
            transaccion.setEstado(estado);
            transaccion.setMonto(monto);
            transaccion.setFecha(LocalDateTime.now());  // Establecemos la fecha de la transacción

            Transaccion createdTransaccion = transaccionService.save(transaccion);
            return new ResponseEntity<>(createdTransaccion, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Eliminar una transacción
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
