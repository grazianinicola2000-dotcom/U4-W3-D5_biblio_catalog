package nicolagraziani.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import nicolagraziani.entities.CatalogItem;
import nicolagraziani.entities.Loan;

import java.util.List;

public class LoanDAO {
    private final EntityManager em;

    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Loan newLoan) {
        EntityTransaction transaction = this.em.getTransaction();
        transaction.begin();
        em.persist(newLoan);
        transaction.commit();
        System.out.println("Il prestito di: " + newLoan.getItem().getTitle() + " inserito a nome di " + newLoan.getUser().getName() + " " + newLoan.getUser().getSurname() + " è stato salvato con successo!");
    }

    public List<CatalogItem> findItemStillOnLoanByMembership(int code) {
        List<CatalogItem> query = em.createQuery("SELECT i.item FROM Loan i WHERE i.user.membershipCode = :code AND i.effectiveReturnDate IS NULL", CatalogItem.class)
                .setParameter("code", code).getResultList();
        if (query.isEmpty()) {
            System.out.println("La lista è vuota");
        }
        return query;
    }

    public List<Loan> findExpiredLoanNotReturned() {
        List<Loan> query = em.createQuery("SELECT i FROM Loan i WHERE i.expectedReturnDate < CURRENT_DATE AND i.effectiveReturnDate IS NULL", Loan.class)
                .getResultList();
        if (query.isEmpty()) {
            System.out.println("Nessun prestito scaduto non ancora restituito");
        }
        return query;
    }
}
