package nicolagraziani.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue
    @Column(name = "loan_id")
    private UUID loanId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "catalog_item_id")
    private CatalogItem item;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "expected_return_date")
    private LocalDate expectedReturnDate;

    @Column(name = "effective_return_date")
    private LocalDate effectiveReturnDate;

    public Loan() {
    }

    public Loan(User user, CatalogItem item, LocalDate startDate, LocalDate effectiveReturnDate) {
        this.user = user;
        this.item = item;
        this.startDate = startDate;
        this.expectedReturnDate = startDate.plusDays(30);
        this.effectiveReturnDate = effectiveReturnDate;
    }

    public UUID getLoanId() {
        return loanId;
    }

    public User getUser() {
        return user;
    }

    public CatalogItem getItem() {
        return item;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getEffectiveReturnDate() {
        return effectiveReturnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", user=" + user +
                ", item=" + item +
                ", startDate=" + startDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", effectiveReturnDate=" + effectiveReturnDate +
                '}';
    }
}
