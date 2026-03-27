package nicolagraziani.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import nicolagraziani.enums.Periodicity;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends CatalogItem {
    @Column(name = "periodicity")
    private Periodicity period;

    public Magazine() {
    }

    public Magazine(int isbn, String title, int publicationYear, int pages, Periodicity period) {
        super(isbn, title, publicationYear, pages);
        this.period = period;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "period=" + period +
                '}';
    }
}
