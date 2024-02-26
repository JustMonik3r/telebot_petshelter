package pro.sky.telebotpetshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.animals.Dog;
import pro.sky.telebotpetshelter.esception.NotFoundException;
import pro.sky.telebotpetshelter.repository.DogRepository;


import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final Logger logger = LoggerFactory.getLogger(DogService.class);

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Dog create(Dog dog) {
        logger.info("Вызван метод create(создание собаки)");
        return dogRepository.save(dog);
    }

    @Override
    public Dog getById(Long id) {
        logger.info("Вызван метод getById(поиск собаки по id)");
        Optional<Dog> dogId = dogRepository.findById(id);
        if (dogId.isEmpty()) {
            throw new NotFoundException("Такой собаки точно нет");
        }
        return dogRepository.findById(id).get();
    }

    @Override
    public Dog update(Dog dog) {
        logger.info("Вызван метод update(обновление собаки по id)");
        Optional<Dog> docId = dogRepository.findById(dog.getId());
        if (docId.isEmpty()) {
            throw new NotFoundException("Такой собаки нет");
        }
        Dog dogUpdate = getById(dog.getId());
        if (dog.getName() != null) {
            dogUpdate.setName(dog.getName());
        }
        if (dog.getAge() != null) {
            dogUpdate.setAge(dog.getAge());
        }
        if (dog.getHealthy() != null) {
            dogUpdate.setHealthy(dog.getHealthy());
        }
        if (dog.getVaccinations() != null) {
            dogUpdate.setVaccinations(dog.getVaccinations());
        }

        return dogRepository.save(dogUpdate);
    }

    @Override
    public void remove(Long id) {
        logger.info("Вызван метод remove(удаление собаки)");
        Dog dogDelete = getById(id);
        dogRepository.deleteById(id);
    }

    @Override
    public List<Dog> getAll() {
        logger.info("Вызван метод getAll(показать всех собак)");
        return dogRepository.findAll();
    }
}
