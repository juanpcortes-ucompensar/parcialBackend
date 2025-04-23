<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Usuario" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
                <%@include file="layout/navbar.jsp"%>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Lista de Usuarios</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Table -->
                        <div class="col-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Apellido</th>
                                        <th scope="col">Correo</th>
                                        <th scope="col">Rol</th>
                                        <th scope="col">Estado</th>
                                        <th scope="col">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                                        int index = 1;
                                        for (Usuario usuario : usuarios) {
                                    %>
                                    <tr>
                                        <td><%= index++ %></td>
                                        <td><%= usuario.getNombre() %></td>
                                        <td><%= usuario.getApellido() %></td>
                                        <td><%= usuario.getEmail() %></td>
                                        <td><%= usuario.getRol() %></td>
                                        <td><%= usuario.getEstado() %></td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="usuarioAlta" method="get">
                                                <input type="hidden" name="usuarioId" value="<%= usuario.getId_usuario() %>">
                                                <button type="submit" class="btn btn-success">Editar</button>
                                            </form>
                                            <form action="svUsuario" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este usuario?');">
                                                <input type="hidden" name="usuarioId" value="<%= usuario.getId_usuario() %>">
                                                <input type="hidden" name="_method" value="DELETE">
                                                <button type="submit" class="btn btn-danger">Eliminar</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>

                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>

            <%@include file="layout/footer.jsp"%>
        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <%@include file="layout/script.jsp"%>

</body>
</html>
