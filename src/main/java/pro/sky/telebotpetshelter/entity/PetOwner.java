package pro.sky.telebotpetshelter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "petOwners")
public class PetOwner {

    @Id
    @Column(name = "id")
    private Long telegramId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "e-mail")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "took_an_animal")
    private boolean tookAnAnimal;


    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PetOwner(Long telegramId, String firstName, String lastName) {
        this.telegramId = telegramId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public PetOwner(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isTookAnAnimal() {
        return tookAnAnimal;
    }

    public void setTookAnAnimal(boolean tookAnAnimal) {
        this.tookAnAnimal = tookAnAnimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetOwner petOwner = (PetOwner) o;
        return tookAnAnimal == petOwner.tookAnAnimal && Objects.equals(telegramId, petOwner.telegramId) && Objects.equals(firstName, petOwner.firstName) && Objects.equals(lastName, petOwner.lastName) && Objects.equals(email, petOwner.email) && Objects.equals(phoneNumber, petOwner.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telegramId, firstName, lastName, email, phoneNumber, tookAnAnimal);
    }

    @Override
    public String toString() {
        return "PetOwner{" +
                "telegramId=" + telegramId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", tookAnAnimal=" + tookAnAnimal +
                '}';
    }
}
