package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.animals.Cat;

import java.util.List;

public interface CatService {
    Cat create(Cat cat);
    Cat getById(Long id);
    Cat update(long id, String name, Integer age, Boolean isHealthy);
    Cat remove(Long id);
    List<Cat> getAll();

}
