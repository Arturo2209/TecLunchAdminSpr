package com.tecsup.teclunchadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "reservaitem")
public class ReservaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    @JsonIgnore  // Ignorar en la serialización para evitar el bucle infinito
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    // Constructor sin argumentos
    public ReservaItem() {}

    // Constructor con argumentos (para crear una nueva instancia con pedido, item y cantidad)
    public ReservaItem(Pedido pedido, Item item, Integer cantidad) {
        this.reserva = pedido.getReserva();  // Esto asume que el pedido ya tiene una reserva asociada
        this.item = item;
        this.cantidad = cantidad;
    }

    // Getters y setters
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
