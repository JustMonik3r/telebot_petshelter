package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.PetOwner;

@Service
public class TextHandler {

    private PetOwnerServiceImpl petOwnerService;
    private final TelegramBot telegramBot;
    private MenuService menuService;

    public TextHandler(PetOwnerServiceImpl petOwnerService, TelegramBot telegramBot, MenuService menuService) {
        this.petOwnerService = petOwnerService;
        this.telegramBot = telegramBot;
        this.menuService = menuService;
    }

    public SendMessage handleMessage(Update update) {
        Long chatId = update.message().chat().id();
        String userText = update.message().text();
        if ("/start".equals(userText)) {
            menuService.getStartMenuShelter(update);
        } else if (update.message().text().matches("(?=.*\\+7\\d{10})(?=.*[а-яА-ЯёЁ]+)(?=.*\\w+@\\w+\\.\\w{2,}).*")) {
            registerPetOwner(update);
            SendMessage message = new SendMessage(chatId, "Ваши данные успешно сохранены. Наш волонтёр свяжется с вами в ближайшее время");
            telegramBot.execute(message);
            return message;
        }
        return null;
    }

    private void registerPetOwner(Update update) {
        String messageText = update.message().text();
        PetOwner petOwner = new PetOwner();
        petOwner.setTelegramId(update.message().chat().id());
        petOwner.setFirstName(messageText);
        petOwner.setLastName(messageText);
        // доделать
        //petOwner.setFirstName(getNameFromMessage(messageText));
        //petOwner.setLastName(getEmailFromMessage(messageText));
        if (!(petOwnerService.existsById(update.message().chat().id()))) {
            petOwnerService.addOwner(petOwner);
        } else {
            petOwnerService.updateOwner(petOwner);
        }
    }

    public SendMessage getVolunteerHelp(Long chatId) {
        SendMessage message = new SendMessage(chatId, "Введите, пожалуйста, имя, телефон (в формате +71234567890) и емейл. Наш волонтер свяжется с Вами в ближайшее время...");
        telegramBot.execute(message);
        return message;

        /*if (petOwnerService.existsByChatId(chatId)) {
            SendMessage message = new SendMessage(chatId, "У нас есть ваши данные для связи с вами. Наш волонтер свяжется с вами в ближайшее время.");
            // доработать сервис заявок на звонок
            telegramBot.execute(message);
            return message;
        } else {
            SendMessage message = new SendMessage(chatId, "Введите пожалуйста ваш номер, имя и электронную почту и наш волонтёр свяжется с вами в ближайшее время. Порядок написания данных не важен.");
            telegramBot.execute(message);
            return message;
        }*/
    }
}
