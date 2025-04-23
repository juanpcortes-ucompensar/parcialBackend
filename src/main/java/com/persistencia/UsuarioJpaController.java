package com.persistencia;

import com.miproyecto.model.Usuario;

import javax.persistence.*;
import java.util.List;

public class UsuarioJpaController {

    private EntityManagerFactory emf;

    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("gestionlibrosPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
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

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public List<Usuario> findUsuarioEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void destroy(int id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.getReference(Usuario.class, id);
            em.remove(usuario);
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

    public void edit(Usuario usuario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            usuario = em.merge(usuario);
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

    public Usuario findUsuarioByUserName(String nombreUsuario) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario", Usuario.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            List<Usuario> usuarios = query.getResultList();
            return (usuarios.isEmpty()) ? null : usuarios.get(0);
        } finally {
            em.close();
        }
    }

    public void createAdminUser() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario admin = new Usuario("admin", "", "admin@correo.com", "admin123", "Admin");
            em.persist(admin);
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

    public Usuario findUsuarioByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email);
            List<Usuario> usuarios = query.getResultList();
            return (usuarios.isEmpty()) ? null : usuarios.get(0);
        } finally {
            em.close();
        }
    }
}
