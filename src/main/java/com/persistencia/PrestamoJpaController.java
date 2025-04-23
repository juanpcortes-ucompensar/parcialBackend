package com.persistencia;

import com.miproyecto.model.Prestamo;

import javax.persistence.*;
import java.util.List;

public class PrestamoJpaController {

    private EntityManagerFactory emf;

    public PrestamoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("gestionlibrosPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un préstamo
    public void create(Prestamo prestamo) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(prestamo);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw re;
        } finally {
            em.close();
        }
    }

    // Editar un préstamo
    public void edit(Prestamo prestamo) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            prestamo = em.merge(prestamo);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw re;
        } finally {
            em.close();
        }
    }

    // Eliminar un préstamo
    public void destroy(Long id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Prestamo prestamo = em.getReference(Prestamo.class, id);
            em.remove(prestamo);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw re;
        } finally {
            em.close();
        }
    }

    // Buscar préstamo por ID
    public Prestamo findPrestamo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prestamo.class, id);
        } finally {
            em.close();
        }
    }

    // Obtener lista completa de préstamos
    public List<Prestamo> findPrestamoEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Prestamo> query = em.createQuery("SELECT p FROM Prestamo p", Prestamo.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar préstamos por usuario ID
    public List<Prestamo> findPrestamosByUsuarioId(Long usuarioId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Prestamo> query = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.usuario.id_usuario = :usuarioId", Prestamo.class);
            query.setParameter("usuarioId", usuarioId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar préstamos por libro ID
    public List<Prestamo> findPrestamosByLibroId(Long libroId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Prestamo> query = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.libro.id_libro = :libroId", Prestamo.class);
            query.setParameter("libroId", libroId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
