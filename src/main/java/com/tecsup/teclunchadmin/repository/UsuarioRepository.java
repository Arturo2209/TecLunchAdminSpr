package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    // Puedes agregar métodos personalizados aquí, por ejemplo:
    Usuario findByCorreo(String correo);
}
