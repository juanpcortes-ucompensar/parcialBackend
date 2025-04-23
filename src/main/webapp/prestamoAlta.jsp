<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Prestamo" %>
<%@ page import="com.miproyecto.model.Usuario" %>
<%@ page import="com.miproyecto.model.Libro" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>

        <%
            Prestamo prestamo = (request.getAttribute("prestamo") == null) ? new Prestamo() : (Prestamo) request.getAttribute("prestamo");
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
            List<Libro> libros = (List<Libro>) request.getAttribute("libros");
        %>

        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@include file="layout/navbar.jsp"%>

                <div class="container-fluid">
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 flex-column">
                        <h1 class="h3 mb-0 text-gray-800">
                            <%= prestamo.getId_prestamo() == null ? "Registrar Préstamo" : "Editar Préstamo" %>
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

                            <form action="prestamoAlta" method="POST">
                                <input type="hidden" name="prestamoId" value="<%= prestamo.getId_prestamo() != null ? prestamo.getId_prestamo() : "" %>">

                                <!-- Usuario -->
                                <div class="form-group">
                                    <label for="usuario">Usuario</label>
                                    <select name="usuarioId" class="form-control" required>
                                        <option value="">-- Seleccione un usuario --</option>
                                        <% for (Usuario u : usuarios) { %>
                                            <option value="<%= u.getId_usuario() %>" <%= prestamo.getUsuario() != null && u.getId_usuario().equals(prestamo.getUsuario().getId_usuario()) ? "selected" : "" %>>
                                                <%= u.getNombre() %> <%= u.getApellido() %> (<%= u.getEmail() %>)
                                            </option>
                                        <% } %>
                                    </select>
                                </div>

                                <!-- Libro -->
                                <div class="form-group">
                                    <label for="libro">Libro</label>
                                    <select name="libroId" class="form-control" required>
                                        <option value="">-- Seleccione un libro --</option>
                                        <% for (Libro l : libros) { %>
                                            <option value="<%= l.getId_libro() %>" <%= prestamo.getLibro() != null && l.getId_libro().equals(prestamo.getLibro().getId_libro()) ? "selected" : "" %>>
                                                <%= l.getTitulo() %> - <%= l.getAutor() %>
                                            </option>
                                        <% } %>
                                    </select>
                                </div>

                                <!-- Fecha de préstamo -->
                                <div class="form-group">
                                    <label for="fecha_prestamo">Fecha de Préstamo</label>
                                    <input type="date" name="fecha_prestamo" class="form-control" value="<%= prestamo.getFecha_prestamo() != null ? prestamo.getFecha_prestamo() : "" %>" required>
                                </div>

                                <!-- Fecha de devolución -->
                                <div class="form-group">
                                    <label for="fecha_devolucion">Fecha de Devolución</label>
                                    <input type="date" name="fecha_devolucion" class="form-control" value="<%= prestamo.getFecha_devolucion() != null ? prestamo.getFecha_devolucion() : "" %>">
                                </div>

                                <!-- Estado -->
                                <div class="form-group">
                                    <label for="estado">Estado</label>
                                    <select name="estado" class="form-control" required>
                                        <option value="">-- Seleccione estado --</option>
                                        <option value="Prestado" <%= "Prestado".equals(prestamo.getEstado()) ? "selected" : "" %>>Prestado</option>
                                        <option value="Devuelto" <%= "Devuelto".equals(prestamo.getEstado()) ? "selected" : "" %>>Devuelto</option>
                                        <option value="No devuelto" <%= "No devuelto".equals(prestamo.getEstado()) ? "selected" : "" %>>No devuelto</option>
                                    </select>
                                </div>

                                <!-- Botón de acción -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= prestamo.getId_prestamo() == null ? "Registrar Préstamo" : "Actualizar Préstamo" %>
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
