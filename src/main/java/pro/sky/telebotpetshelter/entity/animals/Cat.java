package pro.sky.telebotpetshelter.entity.animals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cat_id;

    private String name;

    private Integer age;

    private Boolean isHealthy;

    public Cat(Long cat_id, String name, Integer age, Boolean isHealthy) {
        this.cat_id = cat_id;
        this.name = name;
        this.age = age;
        this.isHealthy = isHealthy;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
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
        return Objects.equals(cat_id, cat.cat_id) && Objects.equals(name, cat.name) && Objects.equals(age, cat.age) && Objects.equals(isHealthy, cat.isHealthy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cat_id, name, age, isHealthy);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "cat_id=" + cat_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isHealthy=" + isHealthy +
                '}';
    }
}
