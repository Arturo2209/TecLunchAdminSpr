package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Retroalimentacion;
import com.tecsup.teclunchadmin.model.Item;
import com.tecsup.teclunchadmin.model.Usuario;
import com.tecsup.teclunchadmin.service.RetroalimentacionService;
import com.tecsup.teclunchadmin.service.ItemService;
import com.tecsup.teclunchadmin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
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

    // Obtener todas las retroalimentaciones
    @GetMapping
    public ResponseEntity<List<Retroalimentacion>> getAllRetroalimentaciones() {
        return ResponseEntity.ok(retroalimentacionService.findAll());
    }

    // Crear una retroalimentación
    @PostMapping
    public ResponseEntity<Retroalimentacion> createRetroalimentacion(@RequestBody Map<String, Object> payload) {
        String idInstitucional = (String) payload.get("idInstitucional");  // Usamos idInstitucional
        Long itemId = Long.valueOf(payload.get("itemId").toString());
        Integer calificacion = Integer.valueOf(payload.get("calificacion").toString());
        String comentario = (String) payload.get("comentario");

        // Buscar usuario y item
        Optional<Usuario> usuario = usuarioService.findById(idInstitucional);  // Usamos idInstitucional
        Optional<Item> item = itemService.findById(itemId);

        // Verificar que el usuario y el item existan
        if (usuario.isPresent() && item.isPresent()) {
            Retroalimentacion retroalimentacion = new Retroalimentacion();
            retroalimentacion.setUsuario(usuario.get());
            retroalimentacion.setItem(item.get());
            retroalimentacion.setCalificacion(calificacion);
            retroalimentacion.setComentario(comentario);
            retroalimentacion.setFechaCreacion(LocalDateTime.now());  // Establecemos la fecha de creación

            // Guardar la retroalimentación
            Retroalimentacion createdRetroalimentacion = retroalimentacionService.save(retroalimentacion);

            // Verificar si se guardó correctamente
            if (createdRetroalimentacion != null) {
                return new ResponseEntity<>(createdRetroalimentacion, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Eliminar una retroalimentación
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
