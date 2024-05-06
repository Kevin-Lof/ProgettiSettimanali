package it.epicode.library.dao;

import it.epicode.library.entities.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class LoanDaoImpl implements LoanDao{

    private static final String PERSISTENCE_UNIT = "JPA_Sample";
    private EntityManagerFactory emf;

    public LoanDaoImpl() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    @Override
    public void addLoan(Loan loan) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(loan);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Loan> getLoansByUserTessera(int tesseraNumber) {
        EntityManager em = emf.createEntityManager();
        List<Loan> loans = em.createQuery("SELECT l FROM Loan l WHERE l.user.numeroTessera = :tesseraNumber", Loan.class)
                .setParameter("tesseraNumber", tesseraNumber)
                .getResultList();
        em.close();
        return loans;
    }

    @Override
    public List<Loan> getOverdueLoans() {
        EntityManager em = emf.createEntityManager();
        List<Loan> overdueLoans = em.createQuery("SELECT l FROM Loan l WHERE l.expectedReturnDate < CURRENT_DATE AND l.actualReturnDate IS NULL", Loan.class)
                .getResultList();
        em.close();
        return overdueLoans;
    }
}