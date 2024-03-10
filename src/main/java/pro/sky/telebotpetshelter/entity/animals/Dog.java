package pro.sky.telebotpetshelter.entity.animals;

import jakarta.persistence.*;
import pro.sky.telebotpetshelter.utils.Vaccinations;

import java.util.Objects;

@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private Boolean isHealthy;
    @Column
    @Enumerated(EnumType.STRING)
    private Vaccinations vaccinations;


    public Dog(Long id, String name, Integer age, Boolean isHealthy, Vaccinations vaccinations) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isHealthy = isHealthy;
        this.vaccinations = vaccinations;
    }

    public Dog(String name, Integer age, Boolean isHealthy, Vaccinations vaccinations) {
        this.name = name;
        this.age = age;
        this.isHealthy = isHealthy;
        this.vaccinations = vaccinations;
    }
    public Dog(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsHealthy() {
        return isHealthy;
    }

    public void setIsHealthy(Boolean healthy) {
        isHealthy = healthy;
    }

    public Vaccinations getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(Vaccinations vaccinations) {
        this.vaccinations = vaccinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(id, dog.id) && Objects.equals(name, dog.name) && Objects.equals(age, dog.age) && Objects.equals(isHealthy, dog.isHealthy) && vaccinations == dog.vaccinations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, isHealthy, vaccinations);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isHealthy=" + isHealthy +
                ", vaccinations=" + vaccinations +
                '}';
    }
}
