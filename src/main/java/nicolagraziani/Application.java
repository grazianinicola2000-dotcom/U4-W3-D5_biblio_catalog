package nicolagraziani;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import nicolagraziani.dao.CatalogItemDAO;
import nicolagraziani.dao.LoanDAO;
import nicolagraziani.dao.UserDAO;
import nicolagraziani.entities.*;
import nicolagraziani.enums.Periodicity;
import nicolagraziani.exceptions.ItemNotFoundException;

import java.time.LocalDate;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("epibook");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        CatalogItemDAO cid = new CatalogItemDAO(em);
        UserDAO ud = new UserDAO(em);
        LoanDAO ld = new LoanDAO(em);

        Book b1 = new Book(100, "Il Signore degli Anelli", 1954, 1200, "Tolkien", "Fantasy");
        Book b2 = new Book(101, "1984", 1949, 328, "Orwell", "Distopia");
        Book b3 = new Book(102, "Il Nome della Rosa", 1980, 512, "Eco", "Storico");
        Book b4 = new Book(103, "Harry Potter e la Pietra Filosofale", 1997, 400, "Rowling", "Fantasy");
        Book b5 = new Book(104, "Il Codice Da Vinci", 2003, 480, "Brown", "Thriller");
        Book b6 = new Book(105, "Shining", 1977, 450, "King", "Horror");
        Book b7 = new Book(106, "Dracula", 1897, 300, "Stoker", "Horror");
        Book b8 = new Book(107, "Dune", 1965, 600, "Herbert", "Sci-Fi");
        Book b9 = new Book(108, "Fahrenheit 451", 1953, 250, "Bradbury", "Distopia");
        Book b10 = new Book(109, "Il Piccolo Principe", 1943, 120, "Saint-Exupéry", "Fiaba");

        Magazine m1 = new Magazine(200, "National Geographic", 2026, 100, Periodicity.MONTHLY);
        Magazine m2 = new Magazine(201, "Time", 2026, 80, Periodicity.WEEKLY);
        Magazine m3 = new Magazine(202, "Focus", 2026, 90, Periodicity.MONTHLY);
        Magazine m4 = new Magazine(203, "Scientific American", 2026, 110, Periodicity.MONTHLY);
        Magazine m5 = new Magazine(204, "Wired", 2026, 85, Periodicity.SEMIANNUAL);
        Magazine m6 = new Magazine(205, "Nature", 2026, 120, Periodicity.WEEKLY);
        Magazine m7 = new Magazine(206, "Forbes", 2026, 95, Periodicity.MONTHLY);
        Magazine m8 = new Magazine(207, "Internazionale", 2026, 70, Periodicity.WEEKLY);
        Magazine m9 = new Magazine(208, "Le Scienze", 2026, 100, Periodicity.SEMIANNUAL);
        Magazine m10 = new Magazine(209, "Panorama", 2026, 75, Periodicity.WEEKLY);

        User u1 = new User("Mario", "Rossi", LocalDate.of(1990, 1, 1), 1001);
        User u2 = new User("Luca", "Bianchi", LocalDate.of(1985, 5, 10), 1002);
        User u3 = new User("Anna", "Verdi", LocalDate.of(2000, 3, 15), 1003);

        User u1FromDB = ud.findUserByMembershipCode(1001);
        User u2FromDB = ud.findUserByMembershipCode(1002);
        User u3FromDB = ud.findUserByMembershipCode(1003);

        CatalogItem ci1FromDB = cid.findByISBN(100);
        CatalogItem ci2FromDB = cid.findByISBN(101);
        CatalogItem ci3FromDB = cid.findByISBN(102);
        CatalogItem ci4FromDB = cid.findByISBN(103);
        CatalogItem ci5FromDB = cid.findByISBN(201);
        CatalogItem ci6FromDB = cid.findByISBN(204);

        Loan l1 = new Loan(u1FromDB, ci1FromDB, LocalDate.now().minusDays(5), null);
        Loan l2 = new Loan(u2FromDB, ci2FromDB, LocalDate.now().minusDays(10), null);
        Loan l3 = new Loan(u1FromDB, ci3FromDB, LocalDate.now().minusDays(40), null);
        Loan l4 = new Loan(u2FromDB, ci4FromDB, LocalDate.now().minusDays(50), null);
        Loan l5 = new Loan(u3FromDB, ci5FromDB, LocalDate.now().minusDays(20), LocalDate.now().minusDays(3));
        Loan l6 = new Loan(u3FromDB, ci6FromDB, LocalDate.now().minusDays(40), LocalDate.now().minusDays(15));


//        AGGIUNTA DI UN ELEMENTO AL CATALOGO
//        cid.save(b1);
//        PRENOTAZIONE
//        ld.save(l1);
//        UTENTE
//        ud.save(u1);


//        RIMOZIONE DAL CATALOGO DATO UN CODICE ISBM
        try {
            cid.deleteByISBN(105);
        } catch (ItemNotFoundException e) {
            System.out.println("!--------ATTENZIONE-------!");
            System.out.println(e.getMessage());
        }

//        RICERCA PER ISBN
        System.out.println("--------Risultato ricerca per ISBN-------");
        try {
            CatalogItem searched = cid.findByISBN(106);
            System.out.println(searched);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }

//        RICERCA PER ANNO DI PUBBLICAZIONE
        System.out.println("--------Risultato ricerca per anno di pubblicazione-------");
        List<CatalogItem> searchedByYear = cid.findByPublicationYear(2026);
        searchedByYear.forEach(System.out::println);

//        RICERCA PER AUTORE(Book)
        System.out.println("--------Risultato ricerca per autore-------");
        List<Book> searchedByAuthor = cid.findByAuthor("brown");
        searchedByAuthor.forEach(System.out::println);

//        RICERCA PER TITOLO O PARTE DI ESSO
        System.out.println("--------Risultato ricerca per titolo o parte di esso-------");
        List<CatalogItem> searchedByPartialTitle = cid.findByTitleStartsWith("i");
        searchedByPartialTitle.forEach(System.out::println);

//        RICERCA ELEMNTI ATTUALMENTE IN PRESTITO DATO UN NUMERO DI TESSERA UTENTE
        System.out.println("--------Risultato ricerca elementi attualmente in prestito dato un numero di tessera utente-------");
        List<CatalogItem> searchedItemStillOnLoan = ld.findItemStillOnLoanByMembership(1001);
        searchedItemStillOnLoan.forEach(System.out::println);

//        RICERCA PRESTITI SCADUTI NON ANCORA RESTITUITI
        System.out.println("--------Risultato ricerca prestiti scaduti non ancora restituiti-------");
        List<Loan> searchedLoan = ld.findExpiredLoanNotReturned();
        searchedLoan.forEach(System.out::println);
        
        em.close();
        emf.close();
    }
}
