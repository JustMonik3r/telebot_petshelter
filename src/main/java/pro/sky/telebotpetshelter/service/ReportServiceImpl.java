package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.entity.Volunteer;
import pro.sky.telebotpetshelter.exceptions.NotFoundException;
import pro.sky.telebotpetshelter.exceptions.ReportNotFoundException;
import pro.sky.telebotpetshelter.repository.ReportRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@Service
public class ReportServiceImpl implements ReportService {

    private  ReportRepository reportRepository;
    private PetOwnerService petOwnerService;
    private  VolunteerService volunteerService;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report findById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isEmpty()) {
        throw new ReportNotFoundException("Отчет не найден");
        }
        return report.get();
    }

    public Iterable<Report> findAll() {
        return reportRepository.findAll();
    }

    public void delete(Long id) {
        reportRepository.deleteById(id);
    }

    @Value("${telegram.bot.token}")
    TelegramBot bot = new TelegramBot("${telegram.bot.token}");

    /**
     * Этот метод проверяет, отправил ли пользователь и фотографию, и описание ниже.
     * Если нет, бот просит отправить фото с описанием.
     * <br>
     * При предоставлении фотографии {@link PhotoSize} записывает размер фотографии. {@link Path} записывает путь к фотографии.
     * Отчет будет сохранен в БД.
     * @throws IOException
     *
     * @param update
     */
    public SendResponse postReport(Update update) {
        Long chatId = update.message().chat().id();
        Report Report = new Report();
        Report.setChatId(chatId);
        Report.setDate(LocalDate.now());
        PhotoSize photoSize = update.message().photo()[update.message().photo().length - 1];
        GetFileResponse getFileResponse = bot.execute(new GetFile(photoSize.fileId()));
        if (getFileResponse.isOk()) {
            try {
                String extension = StringUtils.getFilenameExtension(getFileResponse.file().filePath());
                byte[] image = bot.getFileContent(getFileResponse.file());
                Path write = Files.write(Paths.get(UUID.randomUUID() + "." + extension), image);
                Report.setPhoto(write.toString());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (update.message().caption() == null) {
            SendMessage messageText = new SendMessage(chatId, "Нужно отправить фото вместе с описанием");
            SendResponse response = bot.execute(messageText);
            return response;
        }

        Report.setReportTextUnderPhoto(update.message().caption());
        reportRepository.save(Report);

        SendMessage messageText = new SendMessage(chatId, "Отчет добавлен. Не забывайте отправлять отчеты о вашем питомце ежедневно");
        SendResponse response = bot.execute(messageText);
        return response;
    }

    /**
     * Этот метод ежедневно напоминает об отправке отчета
     * Если владелец не отправлял отчет два дня, бот отправляет уведомление владельцу и волонтеру для связи с первым

     * По завершению испытательного срока, бот либо поздравляет владельца, либо уведомляет его о дополнительном испытательном сроке,
     * либо уведомляет о непрохождении испытания.
     *
     * @return
     */
    @Scheduled(cron = "0 0 12 * * ?")
    private SendResponse sendWarning() {
        LocalDate todayDate = LocalDate.now();
        LocalDate lastReportDate;
        LocalDate twoDaysFromLastReportDate;
        LocalDate probationPeriod;

        for (PetOwner petOwner : petOwnerService.getAllOwners()) {
            if (petOwner.isTookAnAnimal()) {
                SendMessage messageText = new SendMessage(petOwner.getTelegramId(), "Пожалуйста, отправьте отчёт, " +
                        "отправьте одним сообщением фотографии и описание");
                SendResponse response = bot.execute(messageText);
                return response;
            }

            Report lastReport = reportRepository.getLastReportSent(petOwner.getTelegramId());
            lastReportDate = lastReport.getDate();
            twoDaysFromLastReportDate = lastReportDate.plusDays(2);

            Optional<Volunteer> volunteer1 = volunteerService.findAnyVolunteer();
            if (volunteer1.isPresent()) {
                final Volunteer volunteer2 = volunteer1.get();

                if (twoDaysFromLastReportDate.equals(todayDate)) {
                    SendMessage messageText2 = new SendMessage(volunteer2.getTelegramId(),
                            String.format("Последний отчет был принят более двух дней назад у : %s", petOwner.getTelegramId()));
                    bot.execute(messageText2);
                    SendMessage messageText3 = new SendMessage(petOwner.getTelegramId(),
                            "Последний отчет был принят более двух дней назад! Пожалуйста, сдайте отчет, иначе Ваш испытательный срок будет увеличен");
                    SendResponse response3 = bot.execute(messageText3);
                    return response3;
                }
            }

            Report firstReport = reportRepository.getFirstReport(petOwner.getTelegramId());
            probationPeriod = firstReport.getDate().plusDays(30);
            Long numberOfRecordsInTable = reportRepository.getNumberOfRecords(petOwner.getTelegramId());
            if ((probationPeriod.equals(todayDate) && numberOfRecordsInTable == 30) || numberOfRecordsInTable >= 30) {
                SendMessage messageText4 = new SendMessage(petOwner.getTelegramId(), "Поздравляем! " +
                        "Вы прошли испытательный срок.");
                SendResponse response4 = bot.execute(messageText4);
                return response4;
            } else if (!todayDate.equals(probationPeriod)) {
                SendMessage messageText5 = new SendMessage(petOwner.getTelegramId(), "К сожалению, " +
                        "вы не прошли испытательный срок. Пожалуйста, следуйте инструкциям для дальнейших шагов.");
                SendResponse response5 = bot.execute(messageText5);
                return response5;
            }
        }
        return null;
    }
}


