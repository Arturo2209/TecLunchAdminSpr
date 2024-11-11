package com.tecsup.teclunchadmin.service;

import com.tecsup.teclunchadmin.model.Carta;
import com.tecsup.teclunchadmin.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaService {

    @Autowired
    private CartaRepository cartaRepository;

    public List<Carta> findAll() {
        return cartaRepository.findAll();
    }

    public Optional<Carta> findById(Long id) {
        return cartaRepository.findById(id);
    }

    public Carta save(Carta carta) {
        return cartaRepository.save(carta);
    }

    public void deleteById(Long id) {
        cartaRepository.deleteById(id);
    }
}
