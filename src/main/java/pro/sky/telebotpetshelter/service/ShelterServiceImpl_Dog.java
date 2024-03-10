package pro.sky.telebotpetshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.CatShelter;
import pro.sky.telebotpetshelter.entity.DogShelter;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.entity.animals.Dog;
import pro.sky.telebotpetshelter.exceptions.NotFoundException;
import pro.sky.telebotpetshelter.repository.CatShelterRepository;
import pro.sky.telebotpetshelter.repository.DogShelterRepository;
import pro.sky.telebotpetshelter.utils.ModelUtil;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterServiceImpl_Dog implements ShelterService<DogShelter, Dog> {


    private final DogShelterRepository dogShelterRepository;

    public ShelterServiceImpl_Dog(DogShelterRepository dogShelterRepository) {
        this.dogShelterRepository = dogShelterRepository;
    }

    @Override
    public DogShelter addShelter(DogShelter shelter) {
        return dogShelterRepository.save(shelter);
    }

    @Override
    public DogShelter updateShelter(DogShelter dogShelter) {
        DogShelter currentShelter = getShelterById(dogShelter.getId());
        ModelUtil.copyNonNullFields(dogShelter, currentShelter);
        return dogShelterRepository.save(currentShelter);
    }

    @Override
    public DogShelter getShelterById(long id) {
        Optional<DogShelter> shelterId = dogShelterRepository.findById(id);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден.");
        }
        return shelterId.get();
    }

    @Override
    public DogShelter getShelterByName(String name) {
        Optional<DogShelter> shelterId = dogShelterRepository.findByName(name);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден.");
        }
        return shelterId.get();
    }

    @Override
    public List<DogShelter> getAll() {
        return dogShelterRepository.findAll();
    }

    @Override
    public void deleteShelter(long id) {
        dogShelterRepository.deleteById(id);
    }

    public String getInfo() {
        return dogShelterRepository.getInfo();
    }

    @Override
    public CatShelter getAllCatShelterInfo() {
        return null;
    }

    @Override
    public DogShelter getAllDogShelterInfo() {
        return dogShelterRepository.getAllInfo();
    }

    public String getLocation() {
        return dogShelterRepository.getLocation();
    }

    public String getTimetable() {
        return dogShelterRepository.getTimetable();
    }

    public String getSecurity() {
        return dogShelterRepository.getSecurity();
    }

    public String getSafetyAdvice() {
        return dogShelterRepository.getSafetyMeasures();
    }
}
