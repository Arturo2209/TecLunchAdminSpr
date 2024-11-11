package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {
}
