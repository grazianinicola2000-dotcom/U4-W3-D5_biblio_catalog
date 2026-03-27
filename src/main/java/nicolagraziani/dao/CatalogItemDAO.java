package nicolagraziani.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import nicolagraziani.entities.Book;
import nicolagraziani.entities.CatalogItem;
import nicolagraziani.exceptions.ItemNotFoundException;

import java.util.List;

public class CatalogItemDAO {
    private final EntityManager em;

    public CatalogItemDAO(EntityManager em) {
        this.em = em;
    }

    public void save(CatalogItem newItem) {
        EntityTransaction transaction = this.em.getTransaction();
        transaction.begin();
        em.persist(newItem);
        transaction.commit();
        System.out.println("l'Item " + newItem.getTitle() + " è stato salvato con successo!");
    }

    public CatalogItem findByISBN(int isbn) { //NoResultException
        try {
            CatalogItem searched = em.createQuery("SELECT i FROM CatalogItem i WHERE i.isbn = :isbn", CatalogItem.class)
                    .setParameter("isbn", isbn).getSingleResult();
            if (searched == null) throw new ItemNotFoundException(isbn);
            return searched;
        } catch (NoResultException e) {
            throw new ItemNotFoundException(isbn);
        }
    }

    public List<CatalogItem> findByPublicationYear(int pubYear) {
        List<CatalogItem> query = em.createQuery("SELECT i FROM CatalogItem i WHERE i.publicationYear = :pubYear", CatalogItem.class)
                .setParameter("pubYear", pubYear).getResultList();
        if (query.isEmpty()) {
            System.out.println("La lista è vuota");
        }
        return query;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> query = em.createNamedQuery("findByAuthor", Book.class)
                .setParameter("author", author).getResultList();
        if (query.isEmpty()) {
            System.out.println("La lista è vuota");
        }
        return query;
    }

    public List<CatalogItem> findByTitleStartsWith(String parTitle) {
        List<CatalogItem> query = em.createQuery("SELECT i FROM CatalogItem i WHERE LOWER(i.title) LIKE LOWER(:parTitle)", CatalogItem.class)
                .setParameter("parTitle", parTitle + "%").getResultList();
        if (query.isEmpty()) {
            System.out.println("La lista è vuota");
        }
        return query;
    }

    public void deleteByISBN(int isbn) {
        CatalogItem searched = this.findByISBN(isbn);
        EntityTransaction transaction = this.em.getTransaction();
        transaction.begin();
        em.remove(searched);
        transaction.commit();
        System.out.println("L'Item " + searched.getTitle() + " è stato eliminato con successo");
    }
}
