package com.miproyecto.servlets;

import com.miproyecto.model.Libro;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/libroAlta")
public class svAltaLibro extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String libroId = request.getParameter("libroId");
            Libro libro;

            if (libroId == null || libroId.isEmpty()) {
                libro = new Libro(); // Si no hay ID, creamos un libro vacío para la creación
            } else {
                libro = control.traerLibro(Long.parseLong(libroId)); // Cargamos el libro por ID
                if (libro == null) {
                    throw new Exception("No se encontró un libro con ID: " + libroId);
                }
            }

            request.setAttribute("libro", libro);
            request.getRequestDispatcher("/libroAlta.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar el libro: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idLibro = request.getParameter("libroId");
            String titulo = request.getParameter("titulo");
            String autor = request.getParameter("autor");
            String anoParam = request.getParameter("ano");
            String isbn = request.getParameter("isbn");
            String genero = request.getParameter("genero");
            String disponibilidadParam = request.getParameter("disponibilidad");

            Integer ano = (anoParam != null && !anoParam.isEmpty()) ? Integer.parseInt(anoParam) : null;
            Boolean disponibilidad = (disponibilidadParam != null && !disponibilidadParam.isEmpty()) ? Boolean.parseBoolean(disponibilidadParam) : null;

            Libro libro;

            if (idLibro == null || idLibro.isEmpty() || idLibro.equals("0")) {
                libro = new Libro(); // Si no hay ID, creamos un libro vacío para la creación
            } else {
                libro = control.traerLibro(Long.parseLong(idLibro));
                if (libro == null) {
                    throw new Exception("No se encontro un libro con ID: " + idLibro);
                }
            }

            // Validar ISBN único
            Libro libroExistente = control.traerLibroPorIsbn(isbn);
            if (libroExistente != null && libroExistente.getId_libro() != Long.parseLong(idLibro)) {
                request.setAttribute("errorMessage", "El ISBN ya esta en uso.");
                request.getRequestDispatcher("/libroAlta.jsp?libroId="+idLibro).forward(request, response);
                return;
            }

            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setAno(ano);
            libro.setIsbn(isbn);
            libro.setGenero(genero);
            libro.setDisponibilidad(disponibilidad);

            if (idLibro == null || idLibro.isEmpty() || idLibro.equals("0")) {
                control.crearLibro(libro); // Crear libro si no tiene ID
            } else {
                control.editarLibro(libro); // Editar libro si ya tiene ID
            }

            response.sendRedirect("svLibro"); // Redirigir a la lista de libros después de guardar

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al guardar el libro: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
