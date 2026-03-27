package nicolagraziani.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "membership", unique = true)
    private int membershipCode;

    public User() {
    }

    public User(String name, String surname, LocalDate dateOfBirth, int membershipCode) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.membershipCode = membershipCode;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getMembershipCode() {
        return membershipCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", membershipCode=" + membershipCode +
                '}';
    }
}
