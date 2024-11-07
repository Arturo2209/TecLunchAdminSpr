package com.tecsup.teclunchadmin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ReservaItem")
public class ReservaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    // Constructor sin argumentos
    public ReservaItem() {}

    // Constructor con argumentos
    public ReservaItem(Reserva reserva, Item item, Integer cantidad) {
        this.reserva = reserva;
        this.item = item;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ReservaItem{" +
                "id=" + id +
                ", item=" + item +
                ", cantidad=" + cantidad +
                '}';
    }
}
