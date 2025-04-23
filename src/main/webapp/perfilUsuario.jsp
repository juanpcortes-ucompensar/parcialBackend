<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.miproyecto.model.Usuario" %>
<%@include file="layout/header.jsp"%>

<body id="page-top">
<%@include file="layout/validarSesion.jsp"%>
<div id="wrapper">
    <%@include file="layout/sidebar.jsp"%>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@include file="layout/navbar.jsp"%>

            <div class="container-fluid">
                <h1 class="h3 mb-4 text-gray-800">Mi Perfil</h1>

                <%
                    Usuario usuario = (Usuario) request.getAttribute("usuario");
                    if (usuario == null) {
                        response.sendRedirect("login.jsp");
                        return;
                    }

                    String errorMessage = (String) request.getAttribute("errorMessage");
                    String successMessage = (String) request.getAttribute("successMessage");
                %>

                <% if (errorMessage != null) { %>
                    <div class="alert alert-danger"><%= errorMessage %></div>
                <% } %>

                <% if (successMessage != null) { %>
                    <div class="alert alert-success"><%= successMessage %></div>
                <% } %>

                <form action="svPerfilUsuario" method="POST">
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" name="nombre" class="form-control" value="<%= usuario.getNombre() %>" required>
                    </div>

                    <div class="form-group">
                        <label>Apellido</label>
                        <input type="text" name="apellido" class="form-control" value="<%= usuario.getApellido() %>" required>
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="correo" class="form-control" value="<%= usuario.getEmail() %>" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Actualizar Perfil</button>
                </form>
                <form action="svCambiarContrasena" method="POST">
                    <h3>Cambiar Contraseña</h3>

                    <div class="form-group">
                        <label>Contraseña Actual</label>
                        <input type="password" name="claveActual" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>Contraseña Nueva</label>
                        <input type="password" name="claveNueva" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>Repetir Contraseña Nueva</label>
                        <input type="password" name="claveNuevaRepetir" class="form-control" required>
                    </div>

                    <button type="submit" class="btn btn-warning">Actualizar Contraseña</button>
                </form>

            </div>
        </div>

        <%@include file="layout/footer.jsp"%>
    </div>
</div>

<%@include file="layout/script.jsp"%>
</body>
</html>
