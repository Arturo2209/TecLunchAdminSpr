package com.tecsup.teclunchadmin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cartaitem")  // Cambiado para que coincida con el nombre de la tabla en la base de datos
public class CartaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carta_id", nullable = false)
    private Carta carta;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    // Constructor sin argumentos
    public CartaItem() {}

    // Constructor con argumentos
    public CartaItem(Carta carta, Item item) {
        this.carta = carta;
        this.item = item;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "CartaItem{" +
                "id=" + id +
                ", carta=" + carta.getId() +
                ", item=" + item.getId() +
                '}';
    }
}

