package nicolagraziani;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import nicolagraziani.dao.CatalogItemDAO;
import nicolagraziani.entities.Book;
import nicolagraziani.entities.CatalogItem;
import nicolagraziani.entities.Magazine;
import nicolagraziani.enums.Periodicity;
import nicolagraziani.exceptions.ItemNotFoundException;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("epibook");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        CatalogItemDAO cid = new CatalogItemDAO(em);

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

        Magazine m1 = new Magazine(200, "National Geographic", 2024, 100, Periodicity.MONTHLY);
        Magazine m2 = new Magazine(201, "Time", 2024, 80, Periodicity.WEEKLY);
        Magazine m3 = new Magazine(202, "Focus", 2024, 90, Periodicity.MONTHLY);
        Magazine m4 = new Magazine(203, "Scientific American", 2024, 110, Periodicity.MONTHLY);
        Magazine m5 = new Magazine(204, "Wired", 2024, 85, Periodicity.SEMIANNUAL);
        Magazine m6 = new Magazine(205, "Nature", 2024, 120, Periodicity.WEEKLY);
        Magazine m7 = new Magazine(206, "Forbes", 2024, 95, Periodicity.MONTHLY);
        Magazine m8 = new Magazine(207, "Internazionale", 2024, 70, Periodicity.WEEKLY);
        Magazine m9 = new Magazine(208, "Le Scienze", 2024, 100, Periodicity.SEMIANNUAL);
        Magazine m10 = new Magazine(209, "Panorama", 2024, 75, Periodicity.WEEKLY);

//        AGGIUNTA DI UN ELEMENTO A CATALOGO
//        cid.save(b1);
//        cid.save(b2);
//        cid.save(b3);
//        cid.save(b4);
//        cid.save(b5);
//        cid.save(b6);
//        cid.save(b7);
//        cid.save(b8);
//        cid.save(b9);
//        cid.save(b10);
//
//        cid.save(m1);
//        cid.save(m2);
//        cid.save(m3);
//        cid.save(m4);
//        cid.save(m5);
//        cid.save(m6);
//        cid.save(m7);
//        cid.save(m8);
//        cid.save(m9);
//        cid.save(m10);

//        RIMOZIONE DI UN ELEMENTO DEL CATALOGO DATO CODICE ISBM
        try {
            cid.deleteByISBN(105);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }

//        RICERCA DI UN ELEMENTO PER ISBN
        try {
            CatalogItem searched = cid.getByISBN(106);
            System.out.println(searched);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }

        em.close();
        emf.close();
    }
}
