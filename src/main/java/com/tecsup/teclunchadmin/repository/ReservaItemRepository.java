package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.ReservaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaItemRepository extends JpaRepository<ReservaItem, Long> {
}
