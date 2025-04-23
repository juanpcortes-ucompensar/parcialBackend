<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@include file="layout/header.jsp"%>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="row justify-content-center">

            <div class="col-6">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <div class="row">
                            <div class="w-100">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Crear una cuenta</h1>
                                    </div>

                                    <!-- Mostrar error si existe -->
                                    <%
                                        String errorMessage = (String) request.getAttribute("errorMessage");
                                        if (errorMessage != null) {
                                    %>
                                        <div class="alert alert-danger" role="alert">
                                            <%= errorMessage %>
                                        </div>
                                    <% } %>

                                    <form class="user" action="svRegistroUsuario" method="post">
                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <input type="text" class="form-control form-control-user" name="nombre" placeholder="Nombre" required>
                                            </div>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control form-control-user" name="apellido" placeholder="Apellido" required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-user" name="email" placeholder="Correo electrónico" required>
                                        </div>

                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <input type="password" class="form-control form-control-user" name="contrasena" placeholder="Contraseña" required>
                                            </div>
                                            <div class="col-sm-6">
                                                <input type="password" class="form-control form-control-user" name="repetirContrasena" placeholder="Repetir Contraseña" required>
                                            </div>
                                        </div>

                                        <button type="submit" class="btn btn-primary btn-user btn-block">
                                            Registrarse
                                        </button>
                                    </form>

                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="login.jsp">¿Ya tienes cuenta? Inicia sesión</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <%@include file="layout/script.jsp"%>
</body>

</html>
