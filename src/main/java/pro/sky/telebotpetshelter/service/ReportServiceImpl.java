package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.entity.Volunteer;
import pro.sky.telebotpetshelter.exceptions.NotFoundException;
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

    private final ReportRepository reportRepository;
    private PetOwnerServiceImpl petOwnerServiceImpl;

    private PetOwner petOwner;
    private  VolunteerService volunteerService;
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    /**
     * Метод для получения отчета по id из БД
     * @param id идентификатор отчета
     * @return найденный отчет
     */
    public Report findById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isEmpty()) {
            throw new NotFoundException("Отчет не найден");
        }
        return report.get();
    }

    /**
     * Метод для получения всех отчетов из БД
     * @return список найденных отчетов
     */
    public Iterable<Report> findAll() {
        return reportRepository.findAll();
    }

    /**
     * Метод для удаления отчета из БД по id
     * @param id идентификатор отчета
     */
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
     * Trigger every day at 12a.m.
     * Отчет будет сохранен в БД.
     * @throws IOException
     *
     * @param update
     */
    @Scheduled(cron = "0 0 12 * * ?")
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
     * Если владелец не отправлял отчет в течение одного дня, бот отправляет ему соответствующее уведомление
     * Если владелец не отправлял отчет два дня, бот отправляет уведомление владельцу и волонтеру для связи с первым
     * Если владелец не отправлял отчет в течение четырех дней, его испытательный срок считается непройденным.
     * Волонтеру приходит соответсвующее уведомление
     *
     * По завершению испытательного срока, бот либо поздравляет владельца, либо уведомляет его о дополнительном испытательном сроке,
     * либо уведомляет о непрохождении испытания.
     *
     * @return
     */
    @Scheduled(cron = "0 0 12 * * ?")
    private SendResponse sendWarning() {
        LocalDate todayDate = LocalDate.now();
        LocalDate lastReportDate;
        LocalDate firstReportDate;
        LocalDate oneDayFromLastReportDate;
        LocalDate twoDaysFromLastReportDate;
        LocalDate fourDaysFromLastReportDate;
        LocalDate probationPeriodDate;

        Report lastReport = reportRepository.getLastReportSent(petOwner.getTelegramId());
        lastReportDate = lastReport.getDate();

        oneDayFromLastReportDate = lastReportDate.plusDays(1);
        twoDaysFromLastReportDate = lastReportDate.plusDays(2);
        fourDaysFromLastReportDate = lastReportDate.plusDays(4);

        Report firstReport = reportRepository.getFirstReport(petOwner.getTelegramId());
        probationPeriodDate = firstReport.getDate().plusDays(30);

        Long numberOfRecordsInTable = reportRepository.getNumberOfRecords(petOwner.getTelegramId());

        for (PetOwner petOwner : petOwnerServiceImpl.getAllOwners()) {
            SendMessage messageText = new SendMessage(petOwner.getTelegramId(), "Пожалуйста, отправьте отчёт, " +
                    "отправьте одним сообщением фотографию и описание");
            SendResponse response = bot.execute(messageText);
            return response;
        }

        for (PetOwner petOwner : petOwnerServiceImpl.getAllOwners()) {

            if (oneDayFromLastReportDate.equals(todayDate)) {
                SendMessage messageText1 = new SendMessage(petOwner.getTelegramId(), "Вчера мы не получили от Вас " +
                        "отчёт. Будьте внимательнее.");
                SendResponse response1 = bot.execute(messageText1);
                return response1;
            }

            if (twoDaysFromLastReportDate.equals(todayDate)) {

                Optional<Volunteer> volunteer = volunteerService.findAnyVolunteer();
                if (volunteer.isPresent()) {
                    final Volunteer volunteer1 = volunteer.get();

                    SendMessage messageText2 = new SendMessage(volunteer1.getTelegramId(),
                            String.format("Последний отчет был принят более двух дней назад у : %s %s.",
                                    petOwner.getFirstName(), petOwner.getLastName()));
                    SendResponse response2 = bot.execute(messageText2);
                    return response2;
                }
            }

            if (twoDaysFromLastReportDate.equals(todayDate)) {
                SendMessage messageText3 = new SendMessage(petOwner.getTelegramId(),
                        "Последний отчет был принят более двух дней назад! " +
                                "Пожалуйста, сдайте отчет, иначе мы будем вынуждены забрать питомца в приют");
                SendResponse response3 = bot.execute(messageText3);
                return response3;
            }

            if (fourDaysFromLastReportDate.equals(todayDate)) {

                Optional<Volunteer> volunteer = volunteerService.findAnyVolunteer();
                if (volunteer.isPresent()) {
                    final Volunteer volunteer1 = volunteer.get();

                    SendMessage messageText6 = new SendMessage(volunteer1.getTelegramId(),
                            String.format("Последний отчет был принят более четырех дней назад у : %s %s. " +
                                            "Пожалуйста, свяжитесь с усыновителем.",
                                    petOwner.getFirstName(), petOwner.getLastName()));
                    SendResponse response6 = bot.execute(messageText6);
                    return response6;
                }
            }

            if (fourDaysFromLastReportDate.equals(todayDate)) {
                SendMessage messageText7 = new SendMessage(petOwner.getTelegramId(),
                        "Вы не присылали отчет более четырех дней. Пожалуйста, свяжитесь с волонтером" +
                                " для получения инструкции о дальнейших шагах.");
                SendResponse response7 = bot.execute(messageText7);
                return response7;
            }

            if ((probationPeriodDate.equals(todayDate) && numberOfRecordsInTable >= 28)) {
                SendMessage messageText4 = new SendMessage(petOwner.getTelegramId(), "Поздравляем! Вы успешно " +
                        "прошли испытательный срок.");
                SendResponse response4 = bot.execute(messageText4);
                return response4;
            } else if ((probationPeriodDate.equals(todayDate) && numberOfRecordsInTable <= 27 || numberOfRecordsInTable >=24)) {
                SendMessage messageText5 = new SendMessage(petOwner.getTelegramId(), "Вы отправляли отчеты несвоевременно. " +
                        "Ваш испытательный срок будет увеличен.");
                SendResponse response5 = bot.execute(messageText5);
                return response5;
            } else if ((probationPeriodDate.equals(todayDate) && numberOfRecordsInTable < 24 )) {
                SendMessage messageText6 = new SendMessage(petOwner.getTelegramId(), "К сожалению, вы не прошли испытательный срок, " +
                        "пожалуйста, свяжитесь с волонтером для получения инструкции о дальнейших шагахю");
            }
        }
        return null;
    }
}
