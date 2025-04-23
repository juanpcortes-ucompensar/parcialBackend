package com.persistencia;

import com.miproyecto.model.Libro;
import com.miproyecto.model.Usuario;
import com.miproyecto.model.Prestamo;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ControladoraPersistencia {

    private UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    private LibroJpaController libroJpa = new LibroJpaController();
    private PrestamoJpaController prestamoJpa = new PrestamoJpaController();  

    // Métodos para Usuario
    public void crearUsuario(Usuario usuario) throws Exception {
        usuarioJpa.create(usuario);
    }

    public List<Usuario> traerUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    public Usuario traerUsuario(Long id) {
        return usuarioJpa.findUsuario(id);
    }

    public void borrarUsuario(int id) {
        try {
            usuarioJpa.destroy(id);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarUsuario(Usuario usuario) {
        try {
            usuarioJpa.edit(usuario);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Usuario traerUsuarioPorUserName(String nombreUsuario) {
        return usuarioJpa.findUsuarioByUserName(nombreUsuario);
    }

    public void crearUsuarioAdmin() {
        usuarioJpa.createAdminUser();
    }

    public Usuario traerUsuarioPorEmail(String email) {
        return usuarioJpa.findUsuarioByEmail(email);
    }

    // Métodos para Libro
    public void crearLibro(Libro libro) throws Exception {
        libroJpa.create(libro);
    }

    public List<Libro> traerLibros() {
        return libroJpa.findLibroEntities();
    }

    public Libro traerLibro(Long id) {
        return libroJpa.findLibro(id);
    }

    public void borrarLibro(Long id) {
        try {
            libroJpa.destroy(id);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarLibro(Libro libro) {
        try {
            libroJpa.edit(libro);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Libro traerLibroPorIsbn(String isbn) {
        return libroJpa.findLibroByIsbn(isbn);
    }

    public void crearLibroDePrueba() {
        libroJpa.createTestBook(); // Método para crear un libro de prueba
    }

    // Métodos para Prestamo

    public void crearPrestamo(Prestamo prestamo) throws Exception {
        prestamoJpa.create(prestamo);
    }

    public List<Prestamo> traerPrestamos() {
        return prestamoJpa.findPrestamoEntities();
    }

    public Prestamo traerPrestamo(Long id) {
        return prestamoJpa.findPrestamo(id);
    }

    public void borrarPrestamo(Long id) {
        try {
            prestamoJpa.destroy(id);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarPrestamo(Prestamo prestamo) {
        try {
            prestamoJpa.edit(prestamo);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Prestamo> traerPrestamosPorUsuario(Long usuarioId) {
        return prestamoJpa.findPrestamosByUsuarioId(usuarioId);
    }

    public List<Prestamo> traerPrestamosPorLibro(Long libroId) {
        return prestamoJpa.findPrestamosByLibroId(libroId);
    }

}
