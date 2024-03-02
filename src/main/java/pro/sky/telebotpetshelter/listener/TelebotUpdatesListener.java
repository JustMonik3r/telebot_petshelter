package pro.sky.telebotpetshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.repository.DailyReportRepository;

import java.util.List;

@Service
public class TelebotUpdatesListener implements UpdatesListener {
    private Logger logger = LoggerFactory.getLogger(TelebotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    private final DailyReportRepository notificationTaskRepository;

    public TelebotUpdatesListener(DailyReportRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            //Проверяем, получена ли команда /start и отправляем ответное сообщение
            logger.info("Processing update: {}", update);
            Long chatId = update.message().chat().id();
            if (update.message().text().equals("/start")) {
                SendMessage message = new SendMessage(chatId, String.format("Привет, %s! Выбери интересующий тебя пункт меню", update.message().from().firstName()));
                telegramBot.execute(message);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
