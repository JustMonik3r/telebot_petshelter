package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.entity.Volunteer;
import pro.sky.telebotpetshelter.exceptions.ReportNotFoundException;
import pro.sky.telebotpetshelter.repository.PetOwnerRepository;
import pro.sky.telebotpetshelter.repository.ReportRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {
    private  PetOwner petOwner;
    private  ReportRepository reportRepository;
    private  PetOwnerRepository petOwnerRepository;
    private  PetOwnerServiceImpl petOwnerServiceImpl;
    private  VolunteerService volunteerService;

    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportServiceImpl(PetOwner petOwner, PetOwnerServiceImpl petOwnerServiceImpl, ReportRepository reportRepository, PetOwnerRepository petOwnerRepository, VolunteerService volunteerService) {
        this.reportRepository = reportRepository;
        this.petOwnerRepository = petOwnerRepository;
        this.volunteerService = volunteerService;
        this.petOwnerServiceImpl = petOwnerServiceImpl;
    }
    /**
     * Метод для получения отчета по id из БД
     * @param id идентификатор отчета
     * @return найденный отчет
     */
    public Report findById(Long id) {
        logger.info("Поиск отчета по id: " + id);
        return reportRepository.findById(id).orElseThrow(() -> new ReportNotFoundException("Отчет не найден"));
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
     * The method receives the update and checks if the user had sent both a photo and the caption below.
     * If not bot asks to send the photo both with the caption.
     * <br>
     * When photo is provided {@link PhotoSize} writes down the size of the photo. {@link Path} writes the path of the photo.
     * <br>
     * Trigger every day at 12a.m.
     * The report is being saved to the DB.
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

    @Scheduled(cron = "0 0 12 * * ?")
    private SendResponse sendWarning() {
        LocalDate todayDate = LocalDate.now();
        LocalDate lastReportDate;
        LocalDate firstReportDate;
        LocalDate oneDayFromLastReportDate;
        LocalDate twoDaysFromLastReportDate;
        LocalDate fourDaysFromLastReportDate;
        LocalDate probationPeriodDate;
        LocalDate additionalProbationPeriodDate;
        List<PetOwner> petOwnersWithAdditionalPeriod = new ArrayList<>();

        Report lastReport = reportRepository.getLastReportSent(petOwner.getTelegramId());
        lastReportDate = lastReport.getDate();

        oneDayFromLastReportDate = lastReportDate.plusDays(1);
        twoDaysFromLastReportDate = lastReportDate.plusDays(2);
        fourDaysFromLastReportDate = lastReportDate.plusDays(4);

        Report firstReport = reportRepository.getFirstReport(petOwner.getTelegramId());
        probationPeriodDate = firstReport.getDate().plusDays(30);
        additionalProbationPeriodDate = probationPeriodDate.plusDays(14);


        Long numberOfRecordsInTable = reportRepository.getNumberOfRecords(petOwner.getTelegramId());

//            reportDate = ReportRepository.getDateByChatId(petOwner.getTelegramId());

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

//            reportDate = reportRepository.getDateByChatId(petOwner.getTelegramId());

            if ((probationPeriodDate.equals(todayDate) && numberOfRecordsInTable >= 28)) {
                SendMessage messageText4 = new SendMessage(petOwner.getTelegramId(), "Поздравляем! Вы успешно " +
                        "прошли испытательный срок.");
                SendResponse response4 = bot.execute(messageText4);
                return response4;
            } else if ((probationPeriodDate.equals(todayDate) && numberOfRecordsInTable == 27)) {
                SendMessage messageText5 = new SendMessage(petOwner.getTelegramId(), "Вы отправляли отчеты несвоевременно. " +
                        "Ваш испытательный срок увеличен на 14 дней.");
                SendResponse response5 = bot.execute(messageText5);
                return response5;
            }

           /* if ((additionalProbationPeriodDate.equals(todayDate) && numberOfRecordsInTable >= 40)) {
                SendMessage messageText6 = new SendMessage(petOwner.getTelegramId(), "Поздравляем! " +
                        "Вы успешно прошли испытательный срок.");
                SendResponse response6 = bot.execute(messageText6);
                return response6;
            } else if ((additionalProbationPeriodDate.equals(todayDate) && numberOfRecordsInTable <= 39)) {
                SendMessage messageText5 = new SendMessage(petOwner.getTelegramId(), "К сожалению, Вы не прошли испытательный срок. Пожалуйста, свяжитесь с волонтером " +
                        "для получения инструкции о дальнейших шагах.");
                SendResponse response5 = bot.execute(messageText5);
                return response5;
            }*/


        /*} else if (!todayDate.equals(probationPeriodDate)) {
            SendMessage messageText5 = new SendMessage(petOwner.getTelegramId(), "К сожалению, " +
                    "вы не прошли испытательный срок. Пожалуйста, следуйте инструкциям для дальнейших шагов.");
            SendResponse response5 = bot.execute(messageText5);
            return response5;*/
        }
        return null;
    }
}


