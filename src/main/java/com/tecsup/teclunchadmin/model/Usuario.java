package com.tecsup.teclunchadmin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @Column(name = "id_institucional", length = 6)
    private String idInstitucional;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Email
    @Column(unique = true)
    private String correo;

    @NotBlank
    @Column(length = 15)
    private String rol;

    @Lob
    private String preferenciasAlimenticias;

    @Lob
    private String restriccionesDieteticas;

    @Lob
    private String habitosConsumo;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_staff")
    private Boolean isStaff = false;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    // Constructor sin argumentos
    public Usuario() {}

    // Constructor con argumentos
    public Usuario(String idInstitucional, String nombre, String correo, String rol) {
        this.idInstitucional = idInstitucional;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

    // Getters y Setters
    public String getIdInstitucional() {
        return idInstitucional;
    }

    public void setIdInstitucional(String idInstitucional) {
        this.idInstitucional = idInstitucional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPreferenciasAlimenticias() {
        return preferenciasAlimenticias;
    }

    public void setPreferenciasAlimenticias(String preferenciasAlimenticias) {
        this.preferenciasAlimenticias = preferenciasAlimenticias;
    }

    public String getRestriccionesDieteticas() {
        return restriccionesDieteticas;
    }

    public void setRestriccionesDieteticas(String restriccionesDieteticas) {
        this.restriccionesDieteticas = restriccionesDieteticas;
    }

    public String getHabitosConsumo() {
        return habitosConsumo;
    }

    public void setHabitosConsumo(String habitosConsumo) {
        this.habitosConsumo = habitosConsumo;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(Boolean isStaff) {
        this.isStaff = isStaff;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idInstitucional='" + idInstitucional + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
