package com.miproyecto.servlets;

import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/svPerfilUsuario")
public class svPerfilUsuario extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false); // false: no crea una nueva sesión si no existe
            Usuario usuario = (Usuario) control.traerUsuarioPorEmail(session.getAttribute("usuarioEmail").toString());
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/perfilUsuario.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los usuarios: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Método POST: actualizar los datos del perfil
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

            // Obtener el usuario desde la sesión
            String usuarioEmail = session.getAttribute("usuarioEmail").toString();
            Usuario usuario = control.traerUsuarioPorEmail(usuarioEmail);

            // Verificar si el usuario existe
            if (usuario == null) {
                request.setAttribute("errorMessage", "No se encontro el usuario.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
            request.setAttribute("usuario", usuario);

            // Obtener los parámetros del formulario
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String correo = request.getParameter("correo");

            // Validar correo electrónico ya existente
            Usuario usuarioExistente = control.traerUsuarioPorEmail(correo);
            if (usuarioExistente != null && usuarioExistente.getId_usuario() != usuario.getId_usuario()) {
                request.setAttribute("errorMessage", "El correo electronico ya esta en uso.");
                request.getRequestDispatcher("/perfilUsuario.jsp").forward(request, response);
                return;
            }

            // Actualizar los datos del usuario
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(correo);

            // Actualizar en la base de datos
            control.editarUsuario(usuario);

            // Actualizar la sesión con los nuevos datos
            session.setAttribute("usuarioEmail", usuario.getEmail());
            session.setAttribute("nombreCompleto", usuario.getNombre() + " " + usuario.getApellido());

            // Mensaje de éxito
            request.setAttribute("successMessage", "Perfil actualizado con exito.");

            // Redirigir al perfil actualizado
            request.getRequestDispatcher("/perfilUsuario.jsp").forward(request, response);

        } catch (Exception e) {
            // Manejo de error
            request.setAttribute("errorMessage", "Error al actualizar el perfil: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
