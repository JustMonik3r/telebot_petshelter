package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.utils.CallbackDataRequest;
import pro.sky.telebotpetshelter.utils.MessageSender;
@Service
public class ButtonReactionService {
    @Value("${telegram.bot.token}")
    TelegramBot bot = new TelegramBot("${telegram.bot.token}");
    private final MenuService menuService;
    private final TakeAnimalServiceImpl takeAnimalService;
    private final MessageSender messageSender;
    private final ShelterServiceImpl_Cat catShelterService;
    private final ShelterServiceImpl_Dog dogShelterService;
    private final UpdateTextHandlerImpl updateTextHandler;
    private boolean isCat = false;

    public ButtonReactionService(MenuService menuService, TakeAnimalServiceImpl takeAnimalService, MessageSender messageSender, ShelterServiceImpl_Cat catShelterService, ShelterServiceImpl_Dog dogShelterService, UpdateTextHandlerImpl updateTextHandler) {
        this.menuService = menuService;
        this.takeAnimalService = takeAnimalService;
        this.messageSender = messageSender;
        this.catShelterService = catShelterService;
        this.dogShelterService = dogShelterService;
        this.updateTextHandler = updateTextHandler;
    }

    public SendMessage buttonReaction(CallbackQuery callbackQuery) {

        Long chatId = callbackQuery.message().chat().id();
        String data = callbackQuery.data();
        CallbackDataRequest constantByRequest = CallbackDataRequest.getConstantByRequest(data);

        switch (constantByRequest) {

            case CAT:
                isCat = true;
                return menuService.getCatMenu(chatId);
            case DOG:
                isCat = false;
                return menuService.getDogMenu(chatId);
            case GENERAL_SHELTER_INFO:
                return menuService.getInfoAboutShelter(chatId);
            case ABOUT_SHELTER:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getInfo()) : messageSender.sendMessage(chatId, dogShelterService.getInfo());
            case CONTACTS:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getContacts()) : messageSender.sendMessage(chatId, dogShelterService.getContacts());
            case SECURITY:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getSecurity()) : messageSender.sendMessage(chatId, dogShelterService.getSecurity());
            case SAFETY_IN_SHELTER_TERRITORY:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getSafetyAdvice()) : messageSender.sendMessage(chatId, dogShelterService.getSafetyAdvice());
            case HOW_TO_TAKE_ANIMAL:
                return isCat ? menuService.getInfoAboutTakeAnimalCat(chatId) : menuService.getInfoAboutTakeAnimalDog(chatId);
            case GIVE_MY_CONTACT:
                return messageSender.sendMessage(chatId, "Введите пожалуйста ваш номер, имя и электронную почту и наш волонтёр свяжется с " +
                        "вами в ближайшее время.");
            case VOLUNTEER:
                return updateTextHandler.getVolunteerHelp(chatId);
            case ROLLBACK:
                return menuService.getCatAndDogButtonsOnly(chatId);

            case SHELTER_RULES_BEFORE_MEETING_ANIMAL:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getRulesForMeeting(chatId)) : messageSender.sendMessage(chatId, dogShelterService.getRulesForMeeting(chatId));
            case DOCUMENTS_TO_TAKE_ANIMAL:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getDocumentList(chatId)) : messageSender.sendMessage(chatId, dogShelterService.getDocumentList(chatId));
            case TRANSPORTATION_ADVICE:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getRecForTransport(chatId)) : messageSender.sendMessage(chatId, dogShelterService.getRecForTransport(chatId));
            case HOUSE_RULES_FOR_SMALL_ANIMAL:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getHomeRecommendForSmallPet(chatId)) : messageSender.sendMessage(chatId, dogShelterService.getHomeRecommendForSmallPet(chatId));
            case HOUSE_RULES_FOR_ADULT_ANIMAL:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getHomeRecommendForBigPet(chatId)) : messageSender.sendMessage(chatId, dogShelterService.getHomeRecommendForBigPet(chatId));
            case HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getHomeRecommendForDisable(chatId)) : messageSender.sendMessage(chatId, dogShelterService.getHomeRecommendForDisable(chatId));
            case CYNOLOGIST_ADVICE:
                return messageSender.sendMessage(chatId, dogShelterService.getHandlerTips(chatId));
            case CYNOLOGISTS:
                return messageSender.sendMessage(chatId, dogShelterService.getRecForProvenHandlers(chatId));
            case FELINOLOGIST_ADVICE:
                return messageSender.sendMessage(chatId, catShelterService.getHandlerTips(chatId));
            case FELINOLOGISTS:
                return messageSender.sendMessage(chatId, catShelterService.getRecForProvenHandlers(chatId));
            case REFUSE_REASONS:
                return isCat ? messageSender.sendMessage(chatId, catShelterService.getReasonsForRefusal(chatId)) : messageSender.sendMessage(chatId, dogShelterService.getReasonsForRefusal(chatId));
            case REPORT_ANIMAL:
                return messageSender.sendMessage(chatId, "Чтобы бот принял ваш отчет нужно прислать фотографию питомца, и в описании написать " +
                        "рацион животного, общее самочувствие и привыкание к новому месту, а также изменение в поведении. Напишите всё одним сообщением.");
            case TAKE_CAT:
                    return takeAnimalService.getInfoAboutAllCats(chatId);
            case TAKE_DOG:
                    return takeAnimalService.getInfoAboutAllDogs(chatId);
            default:
                return messageSender.sendMessage(chatId, "Обратитесь к волонтеру по телефону: +79012345678");
        }
    }
}
