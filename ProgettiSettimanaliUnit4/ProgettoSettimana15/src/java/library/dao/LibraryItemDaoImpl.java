package it.epicode.library.dao;

import it.epicode.library.entities.LibraryItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import java.util.List;


public class LibraryItemDaoImpl implements LibraryItemDao {


    private static final String PERSISTENCE_UNIT = "JPA_Sample";
    private final EntityManager em;

    public LibraryItemDaoImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        em = emf.createEntityManager();
    }

    @Override
    public boolean isValidIsbn(String code) {
        code = code.replace("-", "");
        return switch (code.length()) {
            case 10 -> checkIsbn10(code);
            case 13 -> checkIsbn13(code);
            default -> false;
        };
    }

    private boolean checkIsbn10(String code) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(code.charAt(i));
            sum += (i + 1) * digit;
        }
        char lastChar = code.charAt(9);
        int lastDigit = (lastChar == 'X') ? 10 : Character.getNumericValue(lastChar);
        sum += 10 * lastDigit;
        return sum % 11 == 0;
    }

    private boolean checkIsbn13(String code) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(code.charAt(i));
            sum += (i % 2 == 0) ? digit : 3 * digit;
        }
        int lastDigit = Character.getNumericValue(code.charAt(12));
        return (10 - (sum % 10)) % 10 == lastDigit;
    }

    @Override
    public void addItem(LibraryItem item) {
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }


    @Override
    public List<LibraryItem> getAllItems() {
        return em.createQuery("SELECT i FROM LibraryItem i", LibraryItem.class)
                .getResultList();
    }

    @Override
    public void removeItemByISBN(String isbn) {
        try {
            em.getTransaction().begin();
            LibraryItem item = getItemByISBN(isbn);
            if (item != null) {
                em.remove(item);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public LibraryItem getItemByISBN(String isbn) {
        try {
            return em.createQuery("SELECT i FROM LibraryItem i WHERE i.isbn = :isbn", LibraryItem.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.out.println("Si è verificato un errore durante la ricerca per ISBN.");
            return null;
        }
    }

    @Override
    public List<LibraryItem> getItemsByPublicationYear(int publicationYear) {
        try {
            return em.createQuery("SELECT i FROM LibraryItem i WHERE i.publicationYear = :publicationYear", LibraryItem.class)
                    .setParameter("publicationYear", publicationYear)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "Si è verificato un errore durante la ricerca per anno di pubblicazione.\n \u001B[0m");
            return null;
        }
    }

    @Override
    public List<LibraryItem> getItemsByAuthor(String author) {
        try {
            return em.createQuery("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(:author)", LibraryItem.class)
                    .setParameter("author", "%" + author.toLowerCase() + "%")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "Si è verificato un errore durante la ricerca per autore.\n \u001B[0m");
            return null;
        }
    }

    @Override
    public List<LibraryItem> getItemsByTitle(String title) {
        try {
            return em.createQuery("SELECT i FROM LibraryItem i WHERE LOWER(i.title) LIKE LOWER(:title)", LibraryItem.class)
                    .setParameter("title", "%" + title.toLowerCase() + "%")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "Si è verificato un errore durante la ricerca per titolo.\n \u001B[0m");
            return null;
        }
    }
}
