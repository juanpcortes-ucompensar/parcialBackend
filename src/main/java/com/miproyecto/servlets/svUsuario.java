package com.miproyecto.servlets;

import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svUsuario")
public class svUsuario extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Usuario> usuarios = control.traerUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/usuarioLista.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los usuarios: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response);
        } else {
            response.sendRedirect("svUsuario");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idUsuario = request.getParameter("usuarioId");
            control.borrarUsuario(Integer.parseInt(idUsuario));
            response.sendRedirect("svUsuario");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al eliminar el usuario: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
