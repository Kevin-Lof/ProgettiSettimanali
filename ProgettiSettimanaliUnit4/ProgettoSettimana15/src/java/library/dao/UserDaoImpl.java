package it.epicode.library.dao;

import it.epicode.library.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class UserDaoImpl implements UserDao{

    private static final String PERSISTENCE_UNIT = "JPA_Sample";
    private EntityManagerFactory emf;
    private EntityManager em;

    public UserDaoImpl() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    @Override
    public void addUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Errore durante l'aggiunta dell'utente", e);
        } finally {
            em.close();
        }
    }

    @Override
    public User findUserByTessera(int tesseraNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.numeroTessera = :tesseraNumber", User.class)
                    .setParameter("tesseraNumber", tesseraNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}
