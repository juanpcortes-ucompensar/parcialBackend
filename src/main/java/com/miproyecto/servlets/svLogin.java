package com.miproyecto.servlets;

import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/svLogin")
public class svLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = control.traerUsuarioPorEmail(email);

        if (usuario != null && usuario.getContrasena().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioEmail", usuario.getEmail());
            session.setAttribute("rol", usuario.getRol());
            session.setAttribute("nombreCompleto", usuario.getNombre() + " " + usuario.getApellido());

            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("errorMessage", "Correo o contrasenia incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("errorMessage");

        int cantUsuarios = control.traerUsuarios().size();
        if (cantUsuarios == 0) {
            control.crearUsuarioAdmin();
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
