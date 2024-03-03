package pro.sky.telebotpetshelter.entity.animals;

import jakarta.persistence.*;
import pro.sky.telebotpetshelter.utils.Vaccinations;

import java.util.Objects;

@Entity
public class Cat {
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


    public Vaccinations getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(Vaccinations vaccinations) {
        this.vaccinations = vaccinations;
    }

    public Cat(Long id, String name, Integer age, Boolean isHealthy, Vaccinations vaccinations) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isHealthy = isHealthy;
        this.vaccinations = vaccinations;
    }

    public Cat(String name, Integer age, Boolean isHealthy, Vaccinations vaccinations) {
        this.name = name;
        this.age = age;
        this.isHealthy = isHealthy;
        this.vaccinations = vaccinations;
    }
    public Cat(){}


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

    public Boolean getHealthy() {
        return isHealthy;
    }

    public void setHealthy(Boolean healthy) {
        isHealthy = healthy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id) && Objects.equals(name, cat.name) && Objects.equals(age, cat.age) && Objects.equals(isHealthy, cat.isHealthy) && vaccinations == cat.vaccinations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, isHealthy, vaccinations);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isHealthy=" + isHealthy +
                ", vaccinations=" + vaccinations +
                '}';
    }
}
