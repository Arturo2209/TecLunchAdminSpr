package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
