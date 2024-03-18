package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.entity.animals.Dog;

import java.util.List;
@Service
public class TakeAnimalServiceImpl {
    private final TelegramBot telegramBot;
    private final PetOwnerServiceImpl petOwnerService;
    private final DogService dogService;
    private final CatService catService;

    public TakeAnimalServiceImpl(TelegramBot telegramBot, PetOwnerServiceImpl animalAdopterService, DogService dogService, CatService catService) {
        this.telegramBot = telegramBot;
        this.petOwnerService = animalAdopterService;
        this.dogService = dogService;
        this.catService = catService;
    }

    public SendMessage getInfoAboutAllCats(Long chatId) {
        List<Cat> cats = catService.getAll();
        SendMessage message = new SendMessage(chatId, cats.toString());
        telegramBot.execute(message);
        return message;
    }

    public SendMessage getInfoAboutAllDogs(Long chatId) {
        List<Dog> dogs = dogService.getAll();
        SendMessage message = new SendMessage(chatId, dogs.toString());
        telegramBot.execute(message);
        return message;
    }
    /**
     * The method is used to set true in the field tookAnimal to designate that animalAdopter had taken an animal.
     * It gets the object of animalAdopter from the DB and sets true to the field tookAnimal.
     * <br>
     * Then edits animalAdopter in the DB.
     * @param chatId
     */
    public void addTookAnimalField(Long chatId) {
        PetOwner petOwner = petOwnerService.getOwnerById(chatId);
        petOwner.setTookAnAnimal(true);
        petOwnerService.addOwner(petOwner);
    }
}
