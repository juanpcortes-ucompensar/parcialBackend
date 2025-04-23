package com.miproyecto.servlets;

import com.miproyecto.model.Libro;
import com.miproyecto.model.Prestamo;
import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/prestamoAlta")
public class svAltaPrestamo extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    // Mostrar formulario (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String prestamoId = request.getParameter("prestamoId");
            Prestamo prestamo;

            if (prestamoId == null || prestamoId.isEmpty()) {
                prestamo = new Prestamo(); // Nuevo préstamo
            } else {
                prestamo = control.traerPrestamo(Long.parseLong(prestamoId));
                if (prestamo == null) {
                    throw new Exception("No se encontró préstamo con ID: " + prestamoId);
                }
            }

            // Cargar datos necesarios para el formulario
            List<Usuario> usuarios = control.traerUsuarios();
            List<Libro> libros = control.traerLibros();

            request.setAttribute("prestamo", prestamo);
            request.setAttribute("usuarios", usuarios);
            request.setAttribute("libros", libros);

            request.getRequestDispatcher("/prestamoAlta.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar el préstamo: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Procesar envío del formulario (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String prestamoId = request.getParameter("prestamoId");
            String usuarioId = request.getParameter("usuarioId");
            String libroId = request.getParameter("libroId");
            String fechaPrestamo = request.getParameter("fecha_prestamo");
            String fechaDevolucion = request.getParameter("fecha_devolucion");
            String estado = request.getParameter("estado");

            Prestamo prestamo;

            if (prestamoId == null || prestamoId.isEmpty()) {
                prestamo = new Prestamo(); // Crear
            } else {
                prestamo = control.traerPrestamo(Long.parseLong(prestamoId)); // Editar
                if (prestamo == null) {
                    throw new Exception("No se encontró el préstamo con ID: " + prestamoId);
                }
            }

            Usuario usuario = control.traerUsuario(Long.parseLong(usuarioId));
            Libro libro = control.traerLibro(Long.parseLong(libroId));

            // Validar disponibilidad del libro
            if (libro.getDisponibilidad() != true) {
                request.setAttribute("errorMessage", "El libro no está disponible.");
                request.getRequestDispatcher("/usuarioAlta.jsp?usuarioId="+prestamoId).forward(request, response);
                return;
            }

            prestamo.setUsuario(usuario);
            prestamo.setLibro(libro);
            prestamo.setFecha_prestamo(LocalDate.parse(fechaPrestamo));
            prestamo.setFecha_devolucion(fechaDevolucion != null && !fechaDevolucion.isEmpty() ? LocalDate.parse(fechaDevolucion) : null);
            prestamo.setEstado(estado);

            if (prestamo.getId_prestamo() == null) {
                control.crearPrestamo(prestamo);
            } else {
                control.editarPrestamo(prestamo);
            }

            response.sendRedirect("svPrestamo"); // Redirigir a la lista

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al guardar el préstamo: " + e.getMessage());

            // Recargar datos necesarios para volver al formulario con datos
            request.setAttribute("usuarios", control.traerUsuarios());
            request.setAttribute("libros", control.traerLibros());
            request.getRequestDispatcher("/prestamoAlta.jsp").forward(request, response);
        }
    }
}
