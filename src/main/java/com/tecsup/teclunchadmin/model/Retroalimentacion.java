package com.tecsup.teclunchadmin.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Retroalimentacion")
public class Retroalimentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // Constructor sin argumentos
    public Retroalimentacion() {}

    // Constructor con argumentos
    public Retroalimentacion(Usuario usuario, Item item, Integer calificacion, String comentario) {
        this.usuario = usuario;
        this.item = item;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaCreacion = LocalDateTime.now();
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Retroalimentacion{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", item=" + item +
                ", calificacion=" + calificacion +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
