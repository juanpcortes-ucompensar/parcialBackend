package com.miproyecto.model;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String email;

    private String contrasena;
    private String rol;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    public enum EstadoUsuario {
        ACTIVO,
        DESACTIVADO
    }

    // Relación uno a muchos con Préstamos
    @OneToMany(mappedBy = "usuario")
    private List<Prestamo> prestamos;

    public Usuario() {
        this.estado = EstadoUsuario.ACTIVO;
    }

    public Usuario(String nombre, String apellido, String email, String contrasena, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estado = EstadoUsuario.ACTIVO;
    }

    // Getters y Setters
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol){
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }
}
