package com.tecsup.teclunchadmin.service;

import com.tecsup.teclunchadmin.model.Retroalimentacion;
import com.tecsup.teclunchadmin.repository.RetroalimentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetroalimentacionService {

    @Autowired
    private RetroalimentacionRepository retroalimentacionRepository;

    public List<Retroalimentacion> findAll() {
        return retroalimentacionRepository.findAll();
    }

    public Optional<Retroalimentacion> findById(Long id) {
        return retroalimentacionRepository.findById(id);
    }

    public Retroalimentacion save(Retroalimentacion retroalimentacion) {
        return retroalimentacionRepository.save(retroalimentacion);
    }

    public void deleteById(Long id) {
        retroalimentacionRepository.deleteById(id);
    }
}
