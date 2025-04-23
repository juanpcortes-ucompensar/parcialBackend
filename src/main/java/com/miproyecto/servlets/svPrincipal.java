package com.miproyecto.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/index")
public class svPrincipal extends HttpServlet {

    //private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        //control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false); // false: no crea una nueva sesión si no existe
            if (session == null || session.getAttribute("usuarioEmail") == null) {
                response.sendRedirect("svLogin");
                return;
            }
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
