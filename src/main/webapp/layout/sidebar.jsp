<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-book"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Biblioteca</div>
    </a>

    <hr class="sidebar-divider my-0">

    <!-- TODOS LOS USUARIOS -->
    <li class="nav-item">
        <a class="nav-link" href="index">
            <i class="fas fa-fw fa-home"></i>
            <span>Inicio</span></a>
    </li>

    <!-- TODOS LOS USUARIOS -->
    <!--<li class="nav-item">
        <a class="nav-link" href="catalogo">
            <i class="fas fa-fw fa-book-open"></i>
            <span>Catálogo de Libros</span></a>
    </li>-->

    <!-- TODOS LOS USUARIOS -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePrestamos"
            aria-expanded="true" aria-controls="collapsePrestamos">
            <i class="fas fa-fw fa-book-reader"></i>
            <span>Préstamos</span>
        </a>
        <div id="collapsePrestamos" class="collapse" aria-labelledby="headingPrestamos" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="svPrestamo">Lista Préstamos</a>
                <a class="collapse-item" href="prestamoAlta">Solicitar Préstamo</a>
            </div>
        </div>
    </li>

    <!-- TODOS LOS USUARIOS -->
    <li class="nav-item">
        <a class="nav-link" href="svPerfilUsuario">
            <i class="fas fa-fw fa-user"></i>
            <span>Mi Perfil</span></a>
    </li>

    <!-- SOLO ADMIN -->
    <!-- Gestión de Libros -->
    <% if(session.getAttribute("rol") != null && session.getAttribute("rol").toString().equalsIgnoreCase("ADMIN")) { %>
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseLibros"
            aria-expanded="true" aria-controls="collapseLibros">
            <i class="fas fa-fw fa-folder"></i>
            <span>Gestión de Libros</span>
        </a>
        <div id="collapseLibros" class="collapse" aria-labelledby="headingLibros" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="svLibro">Lista de Libros</a>
                <a class="collapse-item" href="libroAlta">Registrar Libro</a>
            </div>
        </div>
    </li>

    <!-- SOLO ADMIN -->
    <!-- Gestión de Usuarios -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUsuarios"
            aria-expanded="true" aria-controls="collapseUsuarios">
            <i class="fas fa-fw fa-users-cog"></i>
            <span>Gestión de Usuarios</span>
        </a>
        <div id="collapseUsuarios" class="collapse" aria-labelledby="headingUsuarios" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="svUsuario">Lista de Usuarios</a>
                <a class="collapse-item" href="altaUsuario">Registrar Usuario</a>
            </div>
        </div>
    </li>

    <!-- SOLO ADMIN -->
    <!-- Gestión de Préstamos -->
    <!--<li class="nav-item">
        <a class="nav-link" href="adminPrestamos">
            <i class="fas fa-fw fa-list-alt"></i>
            <span>Todos los Préstamos</span></a>
    </li>-->
    <% } %>

    <hr class="sidebar-divider d-none d-md-block">

    <!-- Cerrar Sesión -->
    <li class="nav-item">
        <a class="nav-link" href="logout">
            <i class="fas fa-fw fa-sign-out-alt"></i>
            <span>Cerrar Sesión</span></a>
    </li>

</ul>
