package pro.sky.telebotpetshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.repository.DailyReportRepository;
import pro.sky.telebotpetshelter.repository.PetOwnerRepository;
import pro.sky.telebotpetshelter.repository.UserNameRepository;
import pro.sky.telebotpetshelter.service.ButtonReactionService;
import pro.sky.telebotpetshelter.service.MenuService;
import pro.sky.telebotpetshelter.service.UpdateTextHandlerImpl;
import pro.sky.telebotpetshelter.service.UserNameService;

import java.util.List;

@Service
public class TelebotUpdatesListener implements UpdatesListener {
    private Logger logger = LoggerFactory.getLogger(TelebotUpdatesListener.class);

    @Value("${telegram.bot.token}")
    TelegramBot bot = new TelegramBot("${telegram.bot.token}");

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserNameService userNameService;
    private UserNameRepository userNameRepository;

    @Autowired
    private PetOwnerRepository petOwnerRepository;
    @Autowired
    private ButtonReactionService buttonReactionService;
    @Autowired
    private UpdateTextHandlerImpl updateTextHandler;
//
//    @Autowired
//    private DailyReportServiceImlp dailyReportService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {

        try {
            updates.forEach(update -> {
                logger.info("Processing update: {}", update);
//            Объявил переменные для имени и номера чата
                if (update.callbackQuery() != null) {
                    buttonReactionService.buttonReaction(update.callbackQuery());
                } else if (update.message().text() != null) {
                    updateTextHandler.handleStartMessage(update);
                } //else if (update.message().photo() != null || update.message().caption() != null) {
//                    dailyReportService.postReport(update);
//                }


            });
        } catch (
                Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
