<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Libro" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>
        <%
            Libro libro = (request.getAttribute("libro") == null) ? new Libro() : (Libro) request.getAttribute("libro");
        %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@include file="layout/navbar.jsp"%>

                <div class="container-fluid">
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 flex-column">
                        <h1 class="h3 mb-0 text-gray-800">
                            <%= libro.getId_libro() == null ? "Crear Libro" : "Actualizar Libro" %>
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
                            <form class="user" action="libroAlta" method="POST">
                                <input type="hidden" name="libroId" value="<%= libro.getId_libro() != null ? libro.getId_libro() : "" %>">

                                <!-- Título -->
                                <div class="form-group">
                                    <label for="titulo">Título</label>
                                    <input type="text" class="form-control form-control-user" id="titulo" name="titulo" value="<%= libro.getTitulo() != null ? libro.getTitulo() : "" %>" required>
                                </div>

                                <!-- Autor -->
                                <div class="form-group">
                                    <label for="autor">Autor</label>
                                    <input type="text" class="form-control form-control-user" id="autor" name="autor" value="<%= libro.getAutor() != null ? libro.getAutor() : "" %>" required>
                                </div>

                                <!-- Año -->
                                <div class="form-group">
                                    <label for="ano">Año</label>
                                    <input type="number" class="form-control form-control-user" id="ano" name="ano" value="<%= libro.getAno() != null ? libro.getAno() : "" %>" required>
                                </div>

                                <!-- ISBN -->
                                <div class="form-group">
                                    <label for="isbn">ISBN</label>
                                    <input type="text" class="form-control form-control-user" id="isbn" name="isbn" value="<%= libro.getIsbn() != null ? libro.getIsbn() : "" %>" required>
                                </div>

                                <!-- Género -->
                                <div class="form-group">
                                    <label for="genero">Género</label>
                                    <input type="text" class="form-control form-control-user" id="genero" name="genero" value="<%= libro.getGenero() != null ? libro.getGenero() : "" %>" required>
                                </div>

                                <!-- Disponibilidad -->
                                <div class="form-group">
                                    <label for="disponibilidad">Disponibilidad</label>
                                    <select class="form-control" id="disponibilidad" name="disponibilidad" required>
                                        <option value="">-- Selecciona disponibilidad --</option>
                                        <option value="true" <%= libro.getDisponibilidad() != null && libro.getDisponibilidad() ? "selected" : "" %>>Disponible</option>
                                        <option value="false" <%= libro.getDisponibilidad() != null && !libro.getDisponibilidad() ? "selected" : "" %>>No Disponible</option>
                                    </select>
                                </div>

                                <!-- Botón de Enviar -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= libro.getId_libro() == null ? "Crear Libro" : "Actualizar Libro" %>
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
