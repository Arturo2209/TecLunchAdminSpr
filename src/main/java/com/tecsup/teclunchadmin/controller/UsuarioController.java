package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Usuario;
import com.tecsup.teclunchadmin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    // Obtener un usuario por su idInstitucional
    @GetMapping("/{idInstitucional}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String idInstitucional) {
        Optional<Usuario> usuario = usuarioService.findById(idInstitucional);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un usuario con un idInstitucional generado automáticamente
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        // Si no tiene idInstitucional, lo asignamos
        if (usuario.getIdInstitucional() == null || usuario.getIdInstitucional().isEmpty()) {
            usuario.setIdInstitucional(usuarioService.generarIdInstitucional());  // Genera un id por defecto
        }
        Usuario createdUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    // Eliminar un usuario por idInstitucional
    @DeleteMapping("/{idInstitucional}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String idInstitucional) {
        Optional<Usuario> existingUsuario = usuarioService.findById(idInstitucional);
        if (existingUsuario.isPresent()) {
            usuarioService.deleteById(idInstitucional);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
