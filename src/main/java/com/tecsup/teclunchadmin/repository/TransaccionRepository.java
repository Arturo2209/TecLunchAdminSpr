package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
