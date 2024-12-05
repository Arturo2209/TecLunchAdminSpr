package com.tecsup.teclunchadmin.service;

import com.tecsup.teclunchadmin.model.Usuario;
import com.tecsup.teclunchadmin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //
    public String generarIdInstitucional() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // Genera un número aleatorio de 6 dígitos
    }

    // Obtener todos los usuarios
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Buscar un usuario por idInstitucional
    public Optional<Usuario> findById(String idInstitucional) {
        return usuarioRepository.findById(idInstitucional);
    }

    // Buscar un usuario por correo
    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    // Guardar un usuario
    public Usuario save(Usuario usuario) {
        // Si el idInstitucional es nulo o vacío, se asigna un id de 6 dígitos aleatorio por defecto
        if (usuario.getIdInstitucional() == null || usuario.getIdInstitucional().isEmpty()) {
            usuario.setIdInstitucional(generarIdInstitucional());
        }

        // Asignar el rol por defecto si no está especificado
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("Estudiante");
        }

        return usuarioRepository.save(usuario);
    }


    // Eliminar un usuario por idInstitucional
    public void deleteById(String idInstitucional) {
        usuarioRepository.deleteById(idInstitucional);
    }
}
