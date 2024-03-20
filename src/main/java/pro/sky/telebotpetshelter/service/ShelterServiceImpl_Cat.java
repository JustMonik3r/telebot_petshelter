package pro.sky.telebotpetshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.CatShelter;
import pro.sky.telebotpetshelter.entity.DogShelter;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.exceptions.NotFoundException;
import pro.sky.telebotpetshelter.repository.CatShelterRepository;
import pro.sky.telebotpetshelter.utils.ModelUtil;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterServiceImpl_Cat implements ShelterService<CatShelter, Cat> {


    private final CatShelterRepository catShelterRepository;

    public ShelterServiceImpl_Cat(CatShelterRepository catShelterRepository) {
        this.catShelterRepository = catShelterRepository;
    }

    @Override
    public CatShelter addShelter(CatShelter shelter) {
        return catShelterRepository.save(shelter);
    }

    @Override
    public CatShelter updateShelter(CatShelter catShelter) {
        CatShelter currentShelter = getShelterById(catShelter.getId());
        ModelUtil.copyNonNullFields(catShelter, currentShelter);
        return catShelterRepository.save(currentShelter);
    }

    @Override
    public CatShelter getShelterById(long id) {
        Optional<CatShelter> shelterId = catShelterRepository.findById(id);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден.");
        }
        return shelterId.get();
    }

    @Override
    public CatShelter getShelterByName(String name) {
        Optional<CatShelter> shelterId = catShelterRepository.findByName(name);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден.");
        }
        return shelterId.get();
    }

    @Override
    public List<CatShelter> getAll() {
        return catShelterRepository.findAll();
    }

    @Override
    public void deleteShelter(long id) {
        catShelterRepository.deleteById(id);
    }

    public String getInfo() {
        return catShelterRepository.getInfo();
    }

    @Override
    public CatShelter getAllCatShelterInfo() {
        return catShelterRepository.getAllInfo();
    }

    @Override
    public DogShelter getAllDogShelterInfo() {
        return null;
    }

    @Override
    public String getContacts() {
        return getLocation() + " " + getTimetable();
    }

    public String getLocation() {
        return catShelterRepository.getLocation();
    }

    public String getTimetable() {
        return catShelterRepository.getTimetable();
    }

    public String getSecurity() {
        return catShelterRepository.getSecurity();
    }

    public String getSafetyAdvice() {
        return catShelterRepository.getSafetyMeasures();
    }

    public String getRulesForMeeting(Long chatId) {
    }

    public String getDocumentList(Long chatId) {
    }

    public String getRecForTransport(Long chatId) {
    }

    public String getHomeRecommendForSmallPet(Long chatId) {
    }

    public String getHomeRecommendForBigPet(Long chatId) {
    }

    public String getHomeRecommendForDisable(Long chatId) {
    }

    public String getHandlerTips(Long chatId) {
    }

    public String getRecForProvenHandlers(Long chatId) {
    }

    public String getReasonsForRefusal(Long chatId) {
    }
}
