package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByNombre(String nombre);
}
