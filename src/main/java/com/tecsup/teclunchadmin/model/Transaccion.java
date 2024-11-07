package com.tecsup.teclunchadmin.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "monto", nullable = false)
    private Double monto;

    // Constructor sin argumentos
    public Transaccion() {}

    // Constructor con argumentos
    public Transaccion(Pedido pedido, String metodoPago, String estado, Double monto) {
        this.pedido = pedido;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.fecha = LocalDateTime.now();
        this.monto = monto;
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

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", metodoPago='" + metodoPago + '\'' +
                ", estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", monto=" + monto +
                '}';
    }
}
