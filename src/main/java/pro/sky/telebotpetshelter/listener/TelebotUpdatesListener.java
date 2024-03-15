package pro.sky.telebotpetshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.service.ButtonReactionService;
import pro.sky.telebotpetshelter.service.ReportService;
import pro.sky.telebotpetshelter.service.TextHandler;

import java.util.List;

@Service
public class TelebotUpdatesListener implements UpdatesListener {
    private final TextHandler textHandler;
    private final Logger logger = LoggerFactory.getLogger(TelebotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    private final ButtonReactionService buttonReactionService;
    private final ReportService reportService;

    public TelebotUpdatesListener(ButtonReactionService buttonReactionService, ReportService reportService, TextHandler textHandler) {
        this.buttonReactionService = buttonReactionService;
        this.reportService = reportService;
        this.textHandler = textHandler;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            try {
                //Проверяем, получена ли команда /start и отправляем ответное сообщение
                logger.info("Processing update: {}", update);
                if (update.callbackQuery() != null) {
                    buttonReactionService.buttonReaction(update.callbackQuery());
                } else if (update.message().text() != null) {
                    textHandler.handleMessage(update);
                } else if (update.message().photo() != null || update.message().caption() != null) {
                    // доделать получение репорта
                    //reportService.postReport(update);
                }
            } catch (RuntimeException e) {
                logger.error(e.getMessage(), e);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
