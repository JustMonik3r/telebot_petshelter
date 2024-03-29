package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.utils.KeyboardUtil;

import static pro.sky.telebotpetshelter.utils.CallbackDataRequest.*;
@Service
public class MenuService {

    @Value("${telegram.bot.token}")
    TelegramBot telegramBot = new TelegramBot("${telegram.bot.token}");
    private final KeyboardUtil keyboardUtil;
    private final UserNameService userNameService;

    public MenuService(KeyboardUtil keyboardUtil, UserNameService userNameService) {
        this.keyboardUtil = keyboardUtil;
        this.userNameService = userNameService;
    }

//    public MenuService(KeyboardUtil keyboardUtil) {
//        this.keyboardUtil = keyboardUtil;
//    }

    public SendMessage getStartMenuShelter(Update update) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(CAT, DOG);
        if (userNameService.newUser(update)) {
            userNameService.registerUser(update);
        }

        SendMessage sendMessage = new SendMessage(update.message().chat().id(), "Добро пожаловать в наш приют! Если вы ищете верного и преданного друга, то пришли по адресу! " +
                " Вы хотите подружиться с кошкой или с " +
                "собакой? Пожалуйста, выберите подходящий приют, чтобы узнать больше.").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    public SendMessage getCatAndDogButtonsOnly(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(CAT, DOG);
        SendMessage sendMessage = new SendMessage(chatId, "Вы вернулись в главное меню").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    public SendMessage getCatMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                GENERAL_SHELTER_INFO,
                HOW_TO_TAKE_ANIMAL,
                REPORT_ANIMAL,
                TAKE_CAT,
                VOLUNTEER,
                ROLLBACK);
        SendMessage sendMessage = new SendMessage(chatId, "Вы выбрали приют для кошек. Что Вас интересует?").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    public SendMessage getDogMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                GENERAL_SHELTER_INFO,
                HOW_TO_TAKE_ANIMAL,
                REPORT_ANIMAL,
                TAKE_DOG,
                VOLUNTEER,
                ROLLBACK);
        SendMessage sendMessage = new SendMessage(chatId, "Вы выбрали приют для собак. Что Вас интересует?").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    public SendMessage getInfoAboutShelter(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                ABOUT_SHELTER,
                CONTACTS,
                SECURITY,
                SAFETY_IN_SHELTER_TERRITORY,
                GIVE_MY_CONTACT,
                VOLUNTEER,
                ROLLBACK);
        SendMessage sendMessage = new SendMessage(chatId, "Выберите интересующую информацию").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    public SendMessage getInfoAboutTakeAnimalDog(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                SHELTER_RULES_BEFORE_MEETING_ANIMAL,
                DOCUMENTS_TO_TAKE_ANIMAL,
                TRANSPORTATION_ADVICE,
                HOUSE_RULES_FOR_SMALL_ANIMAL,
                HOUSE_RULES_FOR_ADULT_ANIMAL,
                HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY,
                CYNOLOGIST_ADVICE,
                CYNOLOGISTS,
                REFUSE_REASONS,
                GIVE_MY_CONTACT,
                ROLLBACK);
        SendMessage sendMessage = new SendMessage(chatId, "Пожалуйста ознакомьтесь с документами. ").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    public SendMessage getInfoAboutTakeAnimalCat(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                SHELTER_RULES_BEFORE_MEETING_ANIMAL,
                DOCUMENTS_TO_TAKE_ANIMAL,
                TRANSPORTATION_ADVICE,
                HOUSE_RULES_FOR_SMALL_ANIMAL,
                HOUSE_RULES_FOR_ADULT_ANIMAL,
                HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY,
                FELINOLOGIST_ADVICE,
                FELINOLOGISTS,
                REFUSE_REASONS,
                GIVE_MY_CONTACT,
                ROLLBACK);
        SendMessage sendMessage = new SendMessage(chatId, "Пожалуйста ознакомьтесь с документами. ").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    /*public SendMessage CatNamesMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(*//*OSCAR, GARFIELD, VASYA,*//* NO);
        SendMessage sendMessage = new SendMessage(chatId,"Если вы ознакомились со всеми условиями нашего приюта и готовы сразу забрать кота, дайте нам об этом знать").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }
    public SendMessage DogNamesMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(*//*TOM, SAMMY, BARSIK,*//* NO);
        SendMessage sendMessage = new SendMessage(chatId,"Если вы ознакомились со всеми условиями нашего приюта и готовы сразу забрать собаку, дайте нам об этом знать").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }*/
}