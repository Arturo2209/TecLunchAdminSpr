package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Item;
import com.tecsup.teclunchadmin.model.Pedido;
import com.tecsup.teclunchadmin.model.PedidoItem;
import com.tecsup.teclunchadmin.service.ItemService;
import com.tecsup.teclunchadmin.service.PedidoItemService;
import com.tecsup.teclunchadmin.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pedidoItems")
public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<PedidoItem> createPedidoItem(@RequestParam Long pedidoId, @RequestParam Long itemId, @RequestParam Integer cantidad) {
        Optional<Pedido> pedido = pedidoService.findById(pedidoId);
        Optional<Item> item = itemService.findById(itemId);
        if (pedido.isPresent() && item.isPresent()) {
            PedidoItem pedidoItem = new PedidoItem(pedido.get(), item.get(), cantidad);
            PedidoItem createdPedidoItem = pedidoItemService.save(pedidoItem);
            return new ResponseEntity<>(createdPedidoItem, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoItem(@PathVariable Long id) {
        Optional<PedidoItem> existingPedidoItem = pedidoItemService.findById(id);
        if (existingPedidoItem.isPresent()) {
            pedidoItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
