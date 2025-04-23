package com.miproyecto.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/svLogout")
public class svLogout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la sesión activa
        HttpSession session = request.getSession(false);  // false: no crea una nueva sesión si no existe

        if (session != null) {
            // Invalidar la sesión
            session.invalidate();
        }

        // Redirigir al usuario a la página de login
        response.sendRedirect("login.jsp");
    }
}
