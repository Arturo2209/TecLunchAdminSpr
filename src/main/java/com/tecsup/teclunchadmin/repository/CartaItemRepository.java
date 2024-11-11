package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.CartaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaItemRepository extends JpaRepository<CartaItem, Long> {
}
