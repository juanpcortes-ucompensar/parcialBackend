package com.miproyecto.servlets;

import com.miproyecto.model.Prestamo;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/svPrestamo")
public class svPrestamo extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    // Mostrar la lista de préstamos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Prestamo> prestamos = control.traerPrestamos(); // Este método debe estar en Controladora
            request.setAttribute("prestamos", prestamos);
            request.getRequestDispatcher("/prestamoLista.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los préstamos: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Manejo de acciones POST (ej: eliminar)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");

        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response);
        } else {
            response.sendRedirect("svPrestamo");
        }
    }

    // Eliminar un préstamo
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idPrestamo = request.getParameter("prestamoId");
            if (idPrestamo != null && !idPrestamo.isEmpty()) {
                control.borrarPrestamo(Long.parseLong(idPrestamo)); // Este método debe existir
            }
            response.sendRedirect("svPrestamo");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al eliminar el préstamo: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
