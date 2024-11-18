package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Item;
import com.tecsup.teclunchadmin.model.Pedido;
import com.tecsup.teclunchadmin.model.ReservaItem;
import com.tecsup.teclunchadmin.service.ItemService;
import com.tecsup.teclunchadmin.service.PedidoService;
import com.tecsup.teclunchadmin.service.ReservaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/reservaItems")
public class ReservaItemController {

    @Autowired
    private ReservaItemService reservaItemService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ItemService itemService;


    @GetMapping
    public ResponseEntity<List<ReservaItem>> getAllReservaItems() {
        List<ReservaItem> reservaItems = reservaItemService.findAll();
        return ResponseEntity.ok(reservaItems);
    }


    @PostMapping
    public ResponseEntity<ReservaItem> createReservaItem(@RequestBody Map<String, Object> payload) {

        // Verifica si alguno de los campos está vacío o nulo
        if (payload.get("pedidoId") == null || payload.get("itemId") == null || payload.get("cantidad") == null) {
            return ResponseEntity.badRequest().body(null); // Si faltan campos, se responde con 400 Bad Request
        }

        Long pedidoId = Long.valueOf(payload.get("pedidoId").toString());
        Long itemId = Long.valueOf(payload.get("itemId").toString());
        Integer cantidad = Integer.valueOf(payload.get("cantidad").toString());

        // Buscar el Pedido y el Item correspondientes
        Optional<Pedido> pedido = pedidoService.findById(pedidoId);
        Optional<Item> item = itemService.findById(itemId);

        if (pedido.isPresent() && item.isPresent()) {
            // Crear el ReservaItem
            ReservaItem reservaItem = new ReservaItem(pedido.get(), item.get(), cantidad);
            ReservaItem createdReservaItem = reservaItemService.save(reservaItem);
            return new ResponseEntity<>(createdReservaItem, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null); // Si no se encuentran, se responde con 400 Bad Request
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservaItem(@PathVariable Long id) {
        Optional<ReservaItem> existingReservaItem = reservaItemService.findById(id);
        if (existingReservaItem.isPresent()) {
            reservaItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); // Si no se encuentra, se responde con 404 Not Found
        }
    }
}
