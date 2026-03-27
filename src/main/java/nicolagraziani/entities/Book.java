package nicolagraziani.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;

@Entity
@DiscriminatorValue("book")
@NamedQuery(name = "findByAuthor", query = "SELECT i FROM Book i WHERE LOWER(i.author) = LOWER(:author)")
public class Book extends CatalogItem {
    private String author;
    private String genre;

    public Book() {
    }

    public Book(int isbn, String title, int publicationYear, int pages, String author, String genre) {
        super(isbn, title, publicationYear, pages);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                " title='" + super.getTitle() + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publication_year='" + super.getPublicationYear() + '\'' +
                ", pages='" + super.getPages() + '\'' +
                ", ISBN='" + super.getIsbn() + '\'' +
                '}';
    }
}
