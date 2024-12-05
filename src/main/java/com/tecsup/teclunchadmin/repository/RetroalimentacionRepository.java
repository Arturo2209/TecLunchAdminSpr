package com.tecsup.teclunchadmin.repository;

import com.tecsup.teclunchadmin.model.Retroalimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetroalimentacionRepository extends JpaRepository<Retroalimentacion, Long> {

}
