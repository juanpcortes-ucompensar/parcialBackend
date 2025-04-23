CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    estado ENUM('ACTIVO', 'DESACTIVADO') NOT NULL DEFAULT 'ACTIVO'
);

CREATE TABLE libros (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    año INT NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    genero VARCHAR(100) NOT NULL,
    disponibilidad BOOLEAN DEFAULT TRUE
);

CREATE TABLE prestamos (
    id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_libro INT,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,

    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_libro FOREIGN KEY (id_libro) REFERENCES libros(id_libro) ON DELETE CASCADE
);
