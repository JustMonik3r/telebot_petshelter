package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class PetShelterInfo {

    public PetShelterInfo(){}

    public SendMessage getRulesForMeeting(Long chatId) {
        return null;
    }

    public SendMessage getDocumentList(Long chatId) {
        return null;
    }

    public SendMessage getRecForTransport(Long chatId) {
        return null;
    }

    public SendMessage getHomeRecommendForSmallPet(Long chatId) {
        return null;
    }

    public SendMessage getHomeRecommendForBigPet(Long chatId) {
        return null;
    }

    public SendMessage getHomeRecommendForDisable(Long chatId) {
        return null;
    }

    public SendMessage getDogHandlerTips(Long chatId) {
        return null;
    }

    public SendMessage getRecForProvenDogHandlers(Long chatId) {
        return null;
    }

    public SendMessage getReasonsForRefusal(Long chatId) {
        return null;
    }
}
