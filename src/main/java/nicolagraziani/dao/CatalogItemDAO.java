package nicolagraziani.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import nicolagraziani.entities.CatalogItem;
import nicolagraziani.exceptions.ItemNotFoundException;

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

    public CatalogItem getByISBN(int isbn) { //NoResultException
        try {
            CatalogItem searched = em.createQuery("SELECT i FROM CatalogItem i WHERE i.isbn = :isbn", CatalogItem.class)
                    .setParameter("isbn", isbn).getSingleResult();
            if (searched == null) throw new ItemNotFoundException(isbn);
            return searched;
        } catch (NoResultException e) {
            throw new ItemNotFoundException(isbn);
        }
    }

    public void deleteByISBN(int isbn) {
        CatalogItem searched = this.getByISBN(isbn);
        EntityTransaction transaction = this.em.getTransaction();
        transaction.begin();
        em.remove(searched);
        transaction.commit();
        System.out.println("L'Item " + searched.getTitle() + " è stato eliminato con successo");
    }
}
