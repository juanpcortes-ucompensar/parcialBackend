package com.miproyecto.servlets;

import com.miproyecto.model.Libro;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svLibro")
public class svLibro extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    // Mostrar la lista de libros
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Libro> libros = control.traerLibros(); // Este método debe existir en tu Controladora
            request.setAttribute("libros", libros);
            request.getRequestDispatcher("/libroLista.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los libros: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Recibir acciones POST (como eliminar)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");

        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response);
        } else {
            response.sendRedirect("svLibro");
        }
    }

    // Eliminar libro
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idLibro = request.getParameter("libroId");
            if (idLibro != null && !idLibro.isEmpty()) {
                control.borrarLibro(Long.parseLong(idLibro)); // Este método también debe existir
            }
            response.sendRedirect("svLibro");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al eliminar el libro: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
