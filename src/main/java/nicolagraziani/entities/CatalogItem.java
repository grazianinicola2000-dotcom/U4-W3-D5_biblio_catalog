package nicolagraziani.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "catalog_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type")
public abstract class CatalogItem {
    @Id
    @GeneratedValue
    @Column(name = "catalog_item_id")
    private UUID itemId;

    @Column(nullable = false, unique = true)
    private int isbn;

    @Column(nullable = false)
    private String title;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(nullable = false)
    private int pages;

    public CatalogItem() {
    }

    public CatalogItem(int isbn, String title, int publicationYear, int pages) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.pages = pages;
    }

    public UUID getItemId() {
        return itemId;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }
    
}
