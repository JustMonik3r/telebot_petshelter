package pro.sky.telebotpetshelter;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telebotpetshelter.repository.DailyReportRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class Scheduler {

    private final DailyReportRepository notificationTaskRepository;
    private final TelegramBot telegramBot;

    public Scheduler(DailyReportRepository notificationTaskRepository, TelegramBot telegramBot) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void sendNotificationTasks() {
        notificationTaskRepository.findAllByExecDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .forEach(notificationTask -> {
                    SendMessage message = new SendMessage(notificationTask.getChatId(), String.format("Пора выполнить задачу: %s", notificationTask.getText()));
                    telegramBot.execute(message);
                });
    }
}
