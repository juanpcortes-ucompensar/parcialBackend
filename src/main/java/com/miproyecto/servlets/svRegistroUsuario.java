package com.miproyecto.servlets;

import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/svRegistroUsuario")
public class svRegistroUsuario extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String contrasena = request.getParameter("contrasena");
            String repetirContrasena = request.getParameter("repetirContrasena");

            // Validar campos
            if (nombre == null || apellido == null || email == null || contrasena == null || repetirContrasena == null ||
                    nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contrasena.isEmpty() || repetirContrasena.isEmpty()) {
                request.setAttribute("errorMessage", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
                return;
            }

            // Validar que las contraseñas coincidan
            if (!contrasena.equals(repetirContrasena)) {
                request.setAttribute("errorMessage", "Las contraseñas no coinciden.");
                request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
                return;
            }

            // Verificar si el email ya existe
            Usuario existente = control.traerUsuarioPorEmail(email);
            if (existente != null) {
                request.setAttribute("errorMessage", "El correo ya está registrado.");
                request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
                return;
            }

            // Crear el usuario
            Usuario nuevo = new Usuario(nombre, apellido, email, contrasena, "USER");
            control.crearUsuario(nuevo);

            // Guardar en sesión e ir al home
            HttpSession session = request.getSession();
            session.setAttribute("usuarioEmail", nuevo.getEmail());
            session.setAttribute("rol", nuevo.getRol());
            session.setAttribute("nombreCompleto", nuevo.getNombre() + " " + nuevo.getApellido());

            response.sendRedirect("index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al registrar el usuario: " + e.getMessage());
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
        }
    }
}
