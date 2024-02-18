package pro.sky.telebotpetshelter.entity.animals;

import java.util.Objects;

public class Dog {

    private Long dog_id;


    private String name;


    private Integer age;


    private Boolean isHealthy;

    public Dog(Long dog_id, String name, Integer age, Boolean isHealthy) {
        this.dog_id = dog_id;
        this.name = name;
        this.age = age;
        this.isHealthy = isHealthy;
    }

    public Long getDog_id() {
        return dog_id;
    }

    public void setDog_id(Long dog_id) {
        this.dog_id = dog_id;
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
        Dog dog = (Dog) o;
        return Objects.equals(dog_id, dog.dog_id) && Objects.equals(name, dog.name) && Objects.equals(age, dog.age) && Objects.equals(isHealthy, dog.isHealthy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dog_id, name, age, isHealthy);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dog_id=" + dog_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isHealthy=" + isHealthy +
                '}';
    }
}
