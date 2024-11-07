package com.tecsup.teclunchadmin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PedidoItem")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    // Constructor sin argumentos
    public PedidoItem() {}

    // Constructor con argumentos
    public PedidoItem(Pedido pedido, Item item, Integer cantidad) {
        this.pedido = pedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
        return "PedidoItem{" +
                "id=" + id +
                ", item=" + item +
                ", cantidad=" + cantidad +
                '}';
    }
}
