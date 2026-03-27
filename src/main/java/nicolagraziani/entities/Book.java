package nicolagraziani.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("book")
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
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
