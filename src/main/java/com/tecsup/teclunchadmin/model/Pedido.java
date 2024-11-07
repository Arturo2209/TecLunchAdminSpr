package com.tecsup.teclunchadmin.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;

    @Column(name = "estado", nullable = false)
    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> pedidoItems;

    // Constructor sin argumentos
    public Pedido() {}

    // Constructor con argumentos
    public Pedido(Usuario usuario, LocalDate fechaPedido, String estado) {
        this.usuario = usuario;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<PedidoItem> getPedidoItems() {
        return pedidoItems;
    }

    public void setPedidoItems(List<PedidoItem> pedidoItems) {
        this.pedidoItems = pedidoItems;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", fechaPedido=" + fechaPedido +
                ", estado='" + estado + '\'' +
                '}';
    }
}
