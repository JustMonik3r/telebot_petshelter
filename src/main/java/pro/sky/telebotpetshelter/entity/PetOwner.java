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
    private Long id;

    @Column(name = "telegram_id")
    private Long telegramId;

    @Column(name = "name")
    private String name;


    @Column(name = "e-mail")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "took_an_animal")
    private boolean tookAnAnimal;

    public PetOwner(Long id, Long telegramId, String name, String email, Long phoneNumber, boolean tookAnAnimal) {
        this.id = id;
        this.telegramId = telegramId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tookAnAnimal = tookAnAnimal;
    }


    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PetOwner(Long telegramId, String name) {
        this.telegramId = telegramId;
        this.name = name;
    }
    //Nik добавил конструктор без ID

    public PetOwner(String name, String email, Long phoneNumber, boolean tookAnAnimal) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tookAnAnimal = tookAnAnimal;
    }

    public PetOwner(Long telegramId, String name, String email, Long phoneNumber, boolean tookAnAnimal) {
        this.telegramId = telegramId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tookAnAnimal = tookAnAnimal;
    }

    @Override
    public String toString() {
        return "PetOwner{" +
                "id=" + id +
                ", telegramId=" + telegramId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", tookAnAnimal=" + tookAnAnimal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetOwner petOwner = (PetOwner) o;
        return tookAnAnimal == petOwner.tookAnAnimal && Objects.equals(id, petOwner.id) && Objects.equals(telegramId, petOwner.telegramId) && Objects.equals(name, petOwner.name) && Objects.equals(email, petOwner.email) && Objects.equals(phoneNumber, petOwner.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telegramId, name, email, phoneNumber, tookAnAnimal);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
