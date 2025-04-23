package com.persistencia;

import com.miproyecto.model.Libro;

import javax.persistence.*;
import java.util.List;

public class LibroJpaController {

    private EntityManagerFactory emf;

    public LibroJpaController() {
        this.emf = Persistence.createEntityManagerFactory("gestionlibrosPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo libro
    public void create(Libro libro) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }

    // Buscar un libro por su ID
    public Libro findLibro(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    // Obtener la lista de todos los libros
    public List<Libro> findLibroEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l", Libro.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Eliminar un libro por su ID
    public void destroy(Long id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Libro libro = em.getReference(Libro.class, id);
            em.remove(libro);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }

    // Editar un libro existente
    public void edit(Libro libro) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            libro = em.merge(libro);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }

    // Buscar un libro por su ISBN
    public Libro findLibroByIsbn(String isbn) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn", Libro.class);
            query.setParameter("isbn", isbn);
            List<Libro> libros = query.getResultList();
            return (libros.isEmpty()) ? null : libros.get(0);
        } finally {
            em.close();
        }
    }

    // Crear un libro de prueba (si lo necesitas)
    public void createTestBook() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Libro libro = new Libro();
            libro.setTitulo("Cien años de soledad");
            libro.setAutor("Gabriel García Márquez");
            libro.setAno(1967);
            libro.setIsbn("978-3-16-148410-0");
            libro.setGenero("Realismo mágico");
            libro.setDisponibilidad(true);
            em.persist(libro);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }
}
