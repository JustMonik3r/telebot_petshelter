package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.InfoHowToTakeAnimal;
import pro.sky.telebotpetshelter.repository.InfoHowToTakeAnimalRepository;
@Service
public class ShelterInfoTakeAnimalImpl {
    private final TelegramBot telegramBot;
    private InfoHowToTakeAnimalRepository infoHowToTakeAnimalRepository;

    public ShelterInfoTakeAnimalImpl(TelegramBot telegramBot, InfoHowToTakeAnimalRepository infoHowToTakeAnimalRepository) {
        this.telegramBot = telegramBot;
        this.infoHowToTakeAnimalRepository = infoHowToTakeAnimalRepository;
    }

    public SendMessage getRulesForMeeting(Long chatId) {
        InfoHowToTakeAnimal infoHowToTakeAnimal = infoHowToTakeAnimalRepository.getALlInfo();
        SendMessage message = new SendMessage(chatId, infoHowToTakeAnimal.getRulesForMeetingAnimal());
        telegramBot.execute(message);
        return message;
    }
    public SendMessage getDocumentList(Long chatId) {
        InfoHowToTakeAnimal infoHowToTakeAnimal = infoHowToTakeAnimalRepository.getALlInfo();
        SendMessage message = new SendMessage(chatId, infoHowToTakeAnimal.getDocumentList());
        telegramBot.execute(message);
        return message;
    }

    public SendMessage getRecForTransport(Long chatId) {
        InfoHowToTakeAnimal infoHowToTakeAnimal = infoHowToTakeAnimalRepository.getALlInfo();
        SendMessage message = new SendMessage(chatId, infoHowToTakeAnimal.getRecForTransport());
        telegramBot.execute(message);
        return message;
    }
    public SendMessage getHomeRecommendForSmall(Long chatId) {
        InfoHowToTakeAnimal infoHowToTakeAnimal = infoHowToTakeAnimalRepository.getALlInfo();
        SendMessage message = new SendMessage(chatId, infoHowToTakeAnimal.getHomeRecommendForSmall());
        telegramBot.execute(message);
        return message;
    }
    public SendMessage getHomeRecommendForBig(Long chatId) {
        InfoHowToTakeAnimal infoHowToTakeAnimal = infoHowToTakeAnimalRepository.getALlInfo();
        SendMessage message = new SendMessage(chatId, infoHowToTakeAnimal.getHomeRecommendForBig());
        telegramBot.execute(message);
        return message;
    }

    public SendMessage getHomeRecommendForDisable(Long chatId) {
        InfoHowToTakeAnimal infoHowToTakeAnimal = infoHowToTakeAnimalRepository.getALlInfo();
        SendMessage message = new SendMessage(chatId, infoHowToTakeAnimal.getHomeRecommendForDisable());
        telegramBot.execute(message);
        return message;
    }
    public SendMessage getDogHandlerTips(Long chatId) {
        InfoHowToTakeAnimal infoHowToTakeAnimal = infoHowToTakeAnimalRepository.getALlInfo();
        SendMessage message = new SendMessage(chatId, infoHowToTakeAnimal.getDogHandlerTips());
        telegramBot.execute(message);
        return message;
    }
    public SendMessage getRecForProvenDogHandlers(Long chatId) {
        InfoHowToTakeAnimal infoHowToTakeAnimal = infoHowToTakeAnimalRepository.getALlInfo();
        SendMessage message = new SendMessage(chatId, infoHowToTakeAnimal.getRecForProvenDogHandlers());
        telegramBot.execute(message);
        return message;
    }
    public SendMessage getReasonsForRefusal(Long chatId) {
        InfoHowToTakeAnimal infoHowToTakeAnimal = infoHowToTakeAnimalRepository.getALlInfo();
        SendMessage message = new SendMessage(chatId, infoHowToTakeAnimal.getReasonsForRefusal());
        telegramBot.execute(message);
        return message;
    }

}
