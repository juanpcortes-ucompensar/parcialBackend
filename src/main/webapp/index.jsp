<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <!--<%@include file="layout/validarSesion.jsp"%>-->
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
                        <h1 class="h3 mb-0 text-gray-800">Bienvenido a la Biblioteca</h1>
                    </div>

                    <!-- Presentación de la Biblioteca -->
                    <div class="row mb-4">
                        <div class="col-12">
                            <p class="text-gray-600">Bienvenido a la biblioteca en línea. Aquí puedes gestionar tus libros, realizar préstamos y gestionar tu perfil. Explora el catálogo y aprovecha los servicios disponibles.</p>
                        </div>
                    </div>

                    <!-- Enlaces Principales -->
                    <div class="row">
                        <div class="col-12 col-md-4">
                            <div class="card shadow mb-4">
                                <div class="card-body text-center">
                                    <h5 class="card-title">Registro</h5>
                                    <p class="card-text">Regístrate para acceder a todos los servicios de la biblioteca.</p>
                                    <a href="registroUsuario.jsp" class="btn btn-primary">Registrarse</a>
                                </div>
                            </div>
                        </div>

                        <div class="col-12 col-md-4">
                            <div class="card shadow mb-4">
                                <div class="card-body text-center">
                                    <h5 class="card-title">Inicio de Sesión</h5>
                                    <p class="card-text">Inicia sesión para gestionar tu perfil y realizar préstamos.</p>
                                    <a href="svLogin" class="btn btn-success">Iniciar Sesión</a>
                                </div>
                            </div>
                        </div>

                        <div class="col-12 col-md-4">
                            <div class="card shadow mb-4">
                                <div class="card-body text-center">
                                    <h5 class="card-title">Catálogo de Libros</h5>
                                    <p class="card-text">Explora los libros disponibles en nuestra biblioteca.</p>
                                    <a href="svPrestamo" class="btn btn-info">Ver Catálogo</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- SOLO ADMIN: Enlace de administración -->
                    <% if(session.getAttribute("rol") != null && session.getAttribute("rol").equals("ADMIN")) { %>
                    <div class="row mt-5">
                        <div class="col-12 text-center">
                            <div class="card shadow mb-4">
                                <div class="card-body">
                                    <h5 class="card-title">Panel de Administración</h5>
                                    <p class="card-text">Gestiona los usuarios, libros y préstamos desde el panel de administración.</p>
                                    <a href="adminDashboard" class="btn btn-danger">Ir al Panel de Administración</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <% } %>

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
