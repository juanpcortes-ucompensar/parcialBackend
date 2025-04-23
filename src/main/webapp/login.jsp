<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@include file="layout/header.jsp"%>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-5">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="w-100">
                                <div class="mx-auto p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">¡Bienvenido de nuevo!</h1>
                                    </div>
                                    <!-- Mostrar mensaje de error si existe -->
                                    <% 
                                        String errorMessage = (String) request.getAttribute("errorMessage");
                                        if (errorMessage != null && !errorMessage.isEmpty()) { 
                                    %>
                                        <div class="alert alert-danger">
                                            <%= errorMessage %>
                                        </div>
                                    <% } %>
                                    <form class="user" action="svLogin" method="post">
                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-user" id="email" name="email" placeholder="Introduce tu correo electrónico..." required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user" id="password" name="password" placeholder="Contraseña" required>
                                        </div>
                                        <button type="submit" class="btn btn-primary btn-user btn-block">
                                            Iniciar sesión
                                        </button>
                                        <div class="text-center mt-3">
                                            <a class="small" href="registroUsuario.jsp">¿No tienes una cuenta? ¡Registrate aquí!</a>
                                        </div>
                                    </form>
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
