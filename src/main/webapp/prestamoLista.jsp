<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Prestamo" %>
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
                        <h1 class="h3 mb-0 text-gray-800">Lista de Préstamos</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Usuario</th>
                                        <th>Libro</th>
                                        <th>Fecha Préstamo</th>
                                        <th>Fecha Devolución</th>
                                        <th>Estado</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");
                                        int index = 1;
                                        if (prestamos != null) {
                                            for (Prestamo prestamo : prestamos) {
                                    %>
                                    <tr>
                                        <td><%= index++ %></td>
                                        <td><%= prestamo.getUsuario().getNombre() + " " + prestamo.getUsuario().getApellido() %></td>
                                        <td><%= prestamo.getLibro().getTitulo() %></td>
                                        <td><%= prestamo.getFecha_prestamo() %></td>
                                        <td><%= prestamo.getFecha_devolucion() != null ? prestamo.getFecha_devolucion() : "-" %></td>
                                        <td><%= prestamo.getEstado() %></td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="prestamoAlta" method="get">
                                                <input type="hidden" name="prestamoId" value="<%= prestamo.getId_prestamo() %>">
                                                <button type="submit" class="btn btn-success btn-sm">Editar</button>
                                            </form>
                                            <form action="svPrestamo" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este préstamo?');">
                                                <input type="hidden" name="prestamoId" value="<%= prestamo.getId_prestamo() %>">
                                                <input type="hidden" name="_method" value="DELETE">
                                                <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
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
