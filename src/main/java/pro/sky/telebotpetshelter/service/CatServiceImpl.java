package pro.sky.telebotpetshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.esception.NotFoundException;
import pro.sky.telebotpetshelter.repository.CatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final Logger logger = LoggerFactory.getLogger(CatService.class);

    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public Cat create(Cat cat) {
        logger.info("Вызван метод create(создание кота)");
        return catRepository.save(cat);
    }

    @Override
    public Cat getById(Long id) {
        logger.info("Вызван метод getById(поиск кота по id)");
        Optional<Cat> catId = catRepository.findById(id);
        if (catId.isEmpty()) {
            throw new NotFoundException("Такого кота точно нет");
        }
        return catRepository.findById(id).get();
    }

    @Override
    public Cat update(Cat cat) {
        logger.info("Вызван метод update(обновление кота по id)");
        Optional<Cat> catId = catRepository.findById(cat.getId());
        if (catId.isEmpty()) {
            throw new NotFoundException("Такого кота нет");
        }
        Cat catUpdate = getById(cat.getId());
        if (cat.getName() != null) {
            catUpdate.setName(cat.getName());
        }
        if (cat.getAge() != null) {
            catUpdate.setAge(cat.getAge());
        }
        if (cat.getHealthy() != null) {
            catUpdate.setHealthy(cat.getHealthy());
        }
        if (cat.getVaccinations() != null) {
            catUpdate.setVaccinations(cat.getVaccinations());
        }

        return catRepository.save(catUpdate);
    }

    @Override
    public void remove(Long id) {
        logger.info("Вызван метод remove(удаление кота)");
        Cat catDelete = getById(id);
        catRepository.deleteById(id);
    }

    @Override
    public List<Cat> getAll() {
        logger.info("Вызван метод getAll(показать всех котов)");
        return catRepository.findAll();
    }


}
