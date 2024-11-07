package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {
}
