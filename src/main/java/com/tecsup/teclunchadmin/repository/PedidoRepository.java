package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}
