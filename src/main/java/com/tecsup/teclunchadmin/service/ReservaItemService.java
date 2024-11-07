package com.tecsup.teclunchadmin.service;

import com.tecsup.teclunchadmin.model.ReservaItem;
import com.tecsup.teclunchadmin.repository.ReservaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaItemService {

    @Autowired
    private ReservaItemRepository reservaItemRepository;

    public List<ReservaItem> findAll() {
        return reservaItemRepository.findAll();
    }

    public Optional<ReservaItem> findById(Long id) {
        return reservaItemRepository.findById(id);
    }

    public ReservaItem save(ReservaItem reservaItem) {
        return reservaItemRepository.save(reservaItem);
    }

    public void deleteById(Long id) {
        reservaItemRepository.deleteById(id);
    }
}
