package nicolagraziani.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import nicolagraziani.entities.User;
import nicolagraziani.exceptions.UserNotFoundException;

public class UserDAO {
    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User newUser) {
        EntityTransaction transaction = this.em.getTransaction();
        transaction.begin();
        em.persist(newUser);
        transaction.commit();
        System.out.println("L'Utente: " + newUser.getName() + " " + newUser.getSurname() + " è stato salvato con successo!");
    }

    public User findUserByMembershipCode(int code) {
        try {
            User searched = em.createQuery("SELECT u FROM  User u WHERE u.membershipCode = :code", User.class)
                    .setParameter("code", code).getSingleResult();
            if (searched == null) throw new UserNotFoundException(code);
            return searched;
        } catch (NoResultException e) {
            throw new UserNotFoundException(code);
        }
    }
}
