package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Reserva;
import com.tecsup.teclunchadmin.model.Usuario;
import com.tecsup.teclunchadmin.service.ReservaService;
import com.tecsup.teclunchadmin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.findById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Map<String, Object> request) {
        String idInstitucional = (String) request.get("idInstitucional");
        Optional<Usuario> usuario = usuarioService.findById(idInstitucional);

        if (usuario.isPresent()) {
            Reserva reserva = new Reserva();
            reserva.setUsuario(usuario.get());

            // Asignar fechaReserva y estado a partir de los datos proporcionados en el JSON
            reserva.setFechaReserva(LocalDate.parse((String) request.get("fechaReserva")));
            reserva.setEstado((String) request.get("estado"));
            reserva.setFechaHoraCreacion(LocalDate.now().atStartOfDay());

            Reserva createdReserva = reservaService.save(reserva);
            return new ResponseEntity<>(createdReserva, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        Optional<Reserva> existingReserva = reservaService.findById(id);
        if (existingReserva.isPresent()) {
            reservaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
