package com.tecsup.teclunchadmin.service;

import com.tecsup.teclunchadmin.model.Usuario;
import com.tecsup.teclunchadmin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(String idInstitucional) {
        return usuarioRepository.findById(idInstitucional);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(String idInstitucional) {
        usuarioRepository.deleteById(idInstitucional);
    }

    public Optional<Usuario> findByCorreo(String correo) {
        return Optional.ofNullable(usuarioRepository.findByCorreo(correo));
    }
}
