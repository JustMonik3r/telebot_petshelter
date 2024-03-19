package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.telebotpetshelter.utils.CallbackDataRequest;
import pro.sky.telebotpetshelter.utils.MessageSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ButtonReactionService {
    @Value("${telegram.bot.token}")
    TelegramBot bot = new TelegramBot("${telegram.bot.token}");
    private final MenuService menuService;
    private final MessageSender messageSender;
    private final PetOwnerService petOwnerService;
    private final ShelterServiceImpl_Cat catShelterService;
    private final ShelterServiceImpl_Dog dogShelterService;
    private final TextHandler textHandler;
    private final PetShelterInfo petShelterInfo;
    private boolean isCat = false;

    public ButtonReactionService(MenuService menuService, MessageSender messageSender, ShelterServiceImpl_Cat catShelterService, ShelterServiceImpl_Dog dogShelterService, PetOwnerService petOwnerService, TextHandler textHandler, PetShelterInfo petShelterInfo) {
        this.menuService = menuService;
        this.messageSender = messageSender;
        this.catShelterService = catShelterService;
        this.petOwnerService = petOwnerService;
        this.dogShelterService = dogShelterService;
        this.textHandler = textHandler;
        this.petShelterInfo = petShelterInfo;
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
                return textHandler.getVolunteerHelp(chatId);
            case ROLLBACK:
                return menuService.getCatAndDogButtonsOnly(chatId);
            case SHELTER_RULES_BEFORE_MEETING_ANIMAL:
                return petShelterInfo.getRulesForMeeting(chatId);
            case DOCUMENTS_TO_TAKE_ANIMAL:
                return petShelterInfo.getDocumentList(chatId);
            case TRANSPORTATION_ADVICE:
                return petShelterInfo.getRecForTransport(chatId);
            case HOUSE_RULES_FOR_SMALL_ANIMAL:
                return petShelterInfo.getHomeRecommendForSmallPet(chatId);
            case HOUSE_RULES_FOR_ADULT_ANIMAL:
                return petShelterInfo.getHomeRecommendForBigPet(chatId);
            case HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY:
                return petShelterInfo.getHomeRecommendForDisable(chatId);
            case CYNOLOGIST_ADVICE:
                return petShelterInfo.getDogHandlerTips(chatId);
            case CYNOLOGISTS:
                return petShelterInfo.getRecForProvenDogHandlers(chatId);
            case FELINOLOGIST_ADVICE:
                return petShelterInfo.getCatHandlerTips(chatId);
            case FELINOLOGISTS:
                return petShelterInfo.getRecForProvenCatHandlers(chatId);
            case REFUSE_REASONS:
                return petShelterInfo.getReasonsForRefusal(chatId);
            case REPORT_ANIMAL:
                return messageSender.sendMessage(chatId, "Чтобы бот принял ваш отчет нужно прислать фотографию питомца, и в описании написать " +
                        "рацион животного, общее самочувствие и привыкание к новому месту, а также изменение в поведении. Напишите всё одним сообщением.");
//            case TAKE_CAT:
//                if (isCat) {
//                    takeAnimalService.getInfoAboutAllCats(chatId);
//                    return menuService.CatNamesMenu(chatId);
//                }
//            case TAKE_DOG:
//                if (!isCat) {
//                    takeAnimalService.getInfoAboutAllDogs(chatId);
//                    return menuService.DogNamesMenu(chatId);
//                }
            default:
                return messageSender.sendMessage(chatId, "Обратитесь к волонтеру по телефону: +79012345678");
        }
    }
}