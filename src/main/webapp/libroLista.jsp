<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Libro" %>
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
                        <h1 class="h3 mb-0 text-gray-800">Lista de Libros</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Table -->
                        <div class="col-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Título</th>
                                        <th scope="col">Autor</th>
                                        <th scope="col">Año</th>
                                        <th scope="col">ISBN</th>
                                        <th scope="col">Género</th>
                                        <th scope="col">Disponible</th>
                                        <th scope="col">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Libro> libros = (List<Libro>) request.getAttribute("libros");
                                        int index = 1;
                                        if (libros != null) {
                                            for (Libro libro : libros) {
                                    %>
                                    <tr>
                                        <td><%= index++ %></td>
                                        <!-- Título con enlace y atributos data-* para mostrar info en el modal -->
                                        <td><a href="javascript:void(0);" class="showBookDetails" 
                                               data-titulo="<%= libro.getTitulo() %>"
                                               data-autor="<%= libro.getAutor() %>"
                                               data-ano="<%= libro.getAno() %>"
                                               data-isbn="<%= libro.getIsbn() %>"
                                               data-genero="<%= libro.getGenero() %>"
                                               data-disponible="<%= libro.getDisponibilidad() ? "Sí" : "No" %>">
                                               <%= libro.getTitulo() %>
                                        </a></td>
                                        <td><%= libro.getAutor() %></td>
                                        <td><%= libro.getAno() %></td>
                                        <td><%= libro.getIsbn() %></td>
                                        <td><%= libro.getGenero() %></td>
                                        <td><%= libro.getDisponibilidad() ? "Sí" : "No" %></td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="libroAlta" method="get">
                                                <input type="hidden" name="libroId" value="<%= libro.getId_libro() %>">
                                                <button type="submit" class="btn btn-success">Editar</button>
                                            </form>
                                            <form action="svLibro" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este libro?');">
                                                <input type="hidden" name="libroId" value="<%= libro.getId_libro() %>">
                                                <input type="hidden" name="_method" value="DELETE">
                                                <button type="submit" class="btn btn-danger">Eliminar</button>
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

    <script>
        document.querySelectorAll('.showBookDetails').forEach(function (bookLink) {
            bookLink.addEventListener('click', function (e) {
                e.preventDefault();
                
                // Obtener la información del libro desde los atributos data-*
                const titulo = bookLink.getAttribute('data-titulo');
                const autor = bookLink.getAttribute('data-autor');
                const ano = bookLink.getAttribute('data-ano');
                const isbn = bookLink.getAttribute('data-isbn');
                const genero = bookLink.getAttribute('data-genero');
                const disponible = bookLink.getAttribute('data-disponible');

                // Mostrar el modal con la información
                Swal.fire({
                    title: titulo,
                    html: 
                        '<strong>Autor:</strong> ' + autor + '<br>' +
                        '<strong>Año:</strong> ' + ano + '<br>' +
                        '<strong>ISBN:</strong> ' + isbn + '<br>' +
                        '<strong>Género:</strong> ' + genero + '<br>' +
                        '<strong>Disponible:</strong> ' + disponible
                    ,
                    icon: 'info',
                    confirmButtonText: 'Cerrar'
                });
            });
        });
    </script>
</body>
</html>
