package com.tecsup.teclunchadmin.service;

import com.tecsup.teclunchadmin.model.CartaItem;
import com.tecsup.teclunchadmin.repository.CartaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaItemService {

    @Autowired
    private CartaItemRepository cartaItemRepository;

    public List<CartaItem> findAll() {
        return cartaItemRepository.findAll();
    }

    public Optional<CartaItem> findById(Long id) {
        return cartaItemRepository.findById(id);
    }

    public CartaItem save(CartaItem cartaItem) {
        return cartaItemRepository.save(cartaItem);
    }

    public void deleteById(Long id) {
        cartaItemRepository.deleteById(id);
    }
}
