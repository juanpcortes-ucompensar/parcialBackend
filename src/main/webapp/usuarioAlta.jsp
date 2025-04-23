<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Usuario" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>
        <%
            Usuario usuario = (request.getAttribute("usuario") == null) ? new Usuario() : (Usuario) request.getAttribute("usuario");
        %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@include file="layout/navbar.jsp"%>

                <div class="container-fluid">
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 flex-column">
                        <h1 class="h3 mb-0 text-gray-800">
                            <%= usuario.getId_usuario() == null ? "Crear Usuario" : "Actualizar Usuario" %>
                        </h1>
                    </div>

                    <div class="row">
                        <div class="col-3"></div>
                        <div class="col-6">
                            <%
                                String errorMessage = (String) request.getAttribute("errorMessage");
                                if (errorMessage != null) {
                            %>
                                <div class="alert alert-danger" role="alert">
                                    <%= errorMessage %>
                                </div>
                            <%
                                }
                            %>
                            <form class="user" action="usuarioAlta" method="POST">
                                <input type="hidden" name="usuarioId" value="<%= usuario.getId_usuario() != null ? usuario.getId_usuario() : "" %>">

                                <!-- Nombre -->
                                <div class="form-group">
                                    <label for="nombre">Nombre</label>
                                    <input type="text" class="form-control form-control-user" id="nombre"
                                           name="nombre" value="<%= usuario.getNombre() != null ? usuario.getNombre() : "" %>" required>
                                </div>

                                <!-- Apellido -->
                                <div class="form-group">
                                    <label for="apellido">Apellido</label>
                                    <input type="text" class="form-control form-control-user" id="apellido"
                                           name="apellido" value="<%= usuario.getApellido() != null ? usuario.getApellido() : "" %>" required>
                                </div>

                                <!-- Email -->
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control form-control-user" id="email"
                                           name="email" value="<%= usuario.getEmail() != null ? usuario.getEmail() : "" %>" required>
                                </div>

                                <!-- Contraseña -->
                                <div class="form-group">
                                    <label for="contrasena">Contraseña</label>
                                    <input type="password" class="form-control form-control-user" id="contrasena"
                                           name="contrasena" value="<%= usuario.getContrasena() != null ? usuario.getContrasena() : "" %>" required>
                                </div>

                                <!-- Rol -->
                                <div class="form-group">
                                    <label for="rol">Rol</label>
                                    <select class="form-control" id="rol" name="rol" required>
                                        <option value="">-- Selecciona un rol --</option>
                                        <option value="ADMIN" <%= "admin".equalsIgnoreCase(usuario.getRol()) ? "selected" : "" %>>Admin</option>
                                        <option value="USER" <%= "user".equalsIgnoreCase(usuario.getRol()) ? "selected" : "" %>>Usuario</option>
                                    </select>
                                </div>

                                <!-- Estado -->
                                <div class="form-group">
                                    <label for="estado">Estado</label>
                                    <select class="form-control" id="estado" name="estado">
                                        <option value="ACTIVO" <%= usuario.getEstado() == Usuario.EstadoUsuario.ACTIVO ? "selected" : "" %>>Activo</option>
                                        <option value="DESACTIVADO" <%= usuario.getEstado() == Usuario.EstadoUsuario.DESACTIVADO ? "selected" : "" %>>Desactivado</option>
                                    </select>
                                </div>

                                <!-- Botón de Enviar -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= usuario.getId_usuario() == null ? "Crear Usuario" : "Actualizar Usuario" %>
                                </button>
                            </form>
                        </div>
                        <div class="col-3"></div>
                    </div>
                </div>
            </div>
            <%@include file="layout/footer.jsp"%>
        </div>
    </div>

    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <%@include file="layout/script.jsp"%>
</body>
</html>
