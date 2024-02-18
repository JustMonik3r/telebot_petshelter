package pro.sky.telebotpetshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.repository.CatRepository;

import java.util.List;
@Service
public class CatServiceImpl implements CatService {

private final CatRepository catRepository;

    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public Cat create(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Cat getById(Long id) {
        return catRepository.findById(id).get();
    }

    @Override
    public Cat update(long id, String name, Integer age, Boolean isHealthy) {
        Cat catUpdate = getById(id);
        catUpdate.setName(name);
        catUpdate.setAge(age);
        catUpdate.setHealthy(isHealthy);
        return catRepository.save(catUpdate);
    }

    @Override
    public Cat remove(Long id) {

        Cat catDelete = getById(id);
        catRepository.deleteById(id);
        return catDelete;
    }

    @Override
    public List<Cat> getAll() {
        return catRepository.findAll();
    }
}
