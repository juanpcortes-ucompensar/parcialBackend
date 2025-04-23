package com.miproyecto.servlets;

import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/svCambiarContrasena")
public class svCambiarContrasena extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Verificar si la sesión está activa
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuarioEmail") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Obtener el usuario de la sesión
            String usuarioEmail = session.getAttribute("usuarioEmail").toString();
            Usuario usuario = control.traerUsuarioPorEmail(usuarioEmail);
            
            request.setAttribute("usuario", usuario);

            // Obtener los parámetros del formulario
            String claveActual = request.getParameter("claveActual");
            String claveNueva = request.getParameter("claveNueva");
            String claveNuevaRepetir = request.getParameter("claveNuevaRepetir");

            // Verificar que las nuevas contraseñas coinciden
            if (!claveNueva.equals(claveNuevaRepetir)) {
                request.setAttribute("errorMessage", "Las contrasenias nuevas no coinciden.");
                request.getRequestDispatcher("/perfilUsuario.jsp").forward(request, response);
                return;
            }

            // Verificar si el usuario existe
            if (usuario == null) {
                request.setAttribute("errorMessage", "Usuario no encontrado.");
                request.getRequestDispatcher("/perfilUsuario.jsp").forward(request, response);
                return;
            }

            // Verificar que la contraseña actual es correcta
            if (!usuario.getContrasena().equals(claveActual)) {
                request.setAttribute("errorMessage", "La contrasenia actual es incorrecta.");
                request.getRequestDispatcher("/perfilUsuario.jsp").forward(request, response);
                return;
            }

            // Actualizar la contraseña del usuario en la base de datos
            usuario.setContrasena(claveNueva);
            control.editarUsuario(usuario);

            // Mensaje de éxito
            request.setAttribute("successMessage", "Contrasenia actualizada correctamente.");
            request.getRequestDispatcher("/perfilUsuario.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cambiar la contraseña: " + e.getMessage());
            request.getRequestDispatcher("/perfilUsuario.jsp").forward(request, response);
        }
    }
}
