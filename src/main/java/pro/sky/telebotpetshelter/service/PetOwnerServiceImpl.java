package pro.sky.telebotpetshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.exceptions.OwnerNotFoundException;
import pro.sky.telebotpetshelter.repository.PetOwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetOwnerServiceImpl implements PetOwnerService {

    private final PetOwnerRepository petOwnerRepository;
    private final Logger logger = LoggerFactory.getLogger(PetOwnerService.class);

    public PetOwnerServiceImpl(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public PetOwner addOwner(PetOwner petOwner) {
        logger.info("Был вызван метод addOwner");
        return petOwnerRepository.save(petOwner);
    }

    @Override
    public PetOwner getOwnerById(Long id) {
        logger.info("Был вызван метод getOwnerById");
        Optional<PetOwner> petOwner = petOwnerRepository.findByTelegramId(id);
        if (petOwner.isEmpty()) {
            throw new OwnerNotFoundException(String.format("Owner [%s] not found", id));
        }
        return petOwner.get();
    }

    @Override
    public List<PetOwner> getAllOwners() {
        logger.info("Был вызван метод getAllOwners");
        return petOwnerRepository.findAll();
    }

    @Override
    public PetOwner updateOwner(PetOwner petOwner) {
        logger.info("Был вызван метод updateOwner");
        PetOwner currentOwner = getOwnerById(petOwner.getTelegramId());
        currentOwner.setFirstName(petOwner.getFirstName());
        currentOwner.setLastName(petOwner.getLastName());
        return petOwnerRepository.save(currentOwner);
    }

    @Override
    public void deleteOwner(Long id) {
        logger.info("Был вызван метод deleteOwner");
        petOwnerRepository.deleteById(id);
    }
}
