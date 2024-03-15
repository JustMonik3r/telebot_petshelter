package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.entity.Volunteer;
import pro.sky.telebotpetshelter.repository.ReportRepository;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class ReportServiceImpl implements ReportService {
    private ReportRepository reportRepository;
    private PetOwnerServiceImpl petOwnerService;
    private VolunteerServiceImpl volunteerService;

    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportServiceImpl(ReportRepository reportRepository, PetOwnerServiceImpl petOwnerService, VolunteerServiceImpl volunteerService) {
        this.reportRepository = reportRepository;
        this.petOwnerService = petOwnerService;
        this.volunteerService = volunteerService;
    }
    /**
     * Метод для получения отчета по id из БД
     * @param id идентификатор отчета
     * @return найденный отчет
     */
    public Report findById(Long id) {
        logger.info("Поиск отчета по id: " + id);
        return reportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Отчет не найден"));
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
//    @Scheduled(cron = "0 0 12 * * ?")
//    public SendResponse postReport(Update update) {
//        Long chatId = update.message().chat().id();
//        Report report = new Report();
//        report.setChatId(chatId);
//        report.setDate(LocalDate.now());
//        PhotoSize photoSize = update.message().photo()[update.message().photo().length - 1];
//        GetFileResponse getFileResponse = bot.execute(new GetFile(photoSize.fileId()));
//        if (getFileResponse.isOk()) {
//            try {
//                String extension = StringUtils.getFilenameExtension(getFileResponse.file().filePath());
//                byte[] image = bot.getFileContent(getFileResponse.file());
//                Path write = Files.write(Paths.get(UUID.randomUUID() + "." + extension), image);
//                report.setPhoto(write.toString());
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        if (update.message().caption() == null) {
//            SendMessage messageText = new SendMessage(chatId, "Нужно отправить фото вместе с описанием");
//            SendResponse response = bot.execute(messageText);
//            return response;
//        }
//
//        report.setReportTextUnderPhoto(update.message().caption());
//        reportRepository.save(report);
//
//        SendMessage messageText = new SendMessage(chatId, "Отчет добавлен. Не забывайте отправлять отчеты о вашем питомце ежедневно");
//        SendResponse response = bot.execute(messageText);
//        return response;
//    }
//
//    @Scheduled(cron = "0 0 12 * * ?")
//    private SendResponse sendWarning() {
//        LocalDate todayDate = LocalDate.now();
//        LocalDate lastReportDate;
//        LocalDate firstReportDate;
//        LocalDate oneDayFromLastReportDate;
//        LocalDate twoDaysFromLastReportDate;
//        LocalDate threeDaysFromLastReportDate;
//        LocalDate probationPeriod;
//        LocalDate additionalProbationPeriod;
//
//        Report lastReport = reportRepository.getLastReportSent();
//        lastReportDate = lastReport.getDate();
//        Long chatId = lastReport.getChatId();
////            reportDate = ReportRepository.getDateByChatId(petOwner.getTelegramId());
//        oneDayFromLastReportDate = lastReportDate.plusDays(1);
//        twoDaysFromLastReportDate = lastReportDate.plusDays(2);
//        threeDaysFromLastReportDate = lastReportDate.plusDays(3);
//
//
//        if (threeDaysFromLastReportDate.equals(todayDate)) {
//            SendMessage messageText6 = new SendMessage(chatId, "Вы не отправляли" +
//                    "отчёты 3 дня!");
//            SendResponse response6 = bot.execute(messageText6);
//            return response6;
//        }
//
//        for (PetOwner petOwner : petOwnerService.getAllOwners()) {
//            SendMessage messageText = new SendMessage(petOwner.getTelegramId(), "Пожалуйста, отправьте отчёт, " +
//                    "отправьте одним сообщением фотографии и описание");
//            SendResponse response = bot.execute(messageText);
//            return response;
//        }
//
//        if (oneDayFromLastReportDate.equals(todayDate)) {
//            SendMessage messageText1 = new SendMessage(chatId, "Вы не отправили " +
//                    "отчёт за прошлый день");
//            SendResponse response1 = bot.execute(messageText1);
//            return response1;
//        }
//
//        Optional<Volunteer> volunteer = volunteerService.findAnyVolunteer();
//
//        // доделать поиск овнера по ид нормально
//        PetOwner petOwner = petOwnerService.getOwnerById(chatId);
//
//        if(volunteer.isPresent()) {
//            final Volunteer volunteer1 = volunteer.get();
//
//            if (twoDaysFromLastReportDate.equals(todayDate)){
//                SendMessage messageText2 = new SendMessage(volunteer1.getTelegramId(),
//                                                           String.format("Последний отчет был принят более двух дней назад у : %s %s." ,
//                                                                         petOwner.getFirstName(), petOwner.getLastName()));
//
//                SendMessage messageText3 = new SendMessage(chatId,
//                                                           "Последний отчет был принят более двух дней назад! Пожалуйста, сдайте отчет, иначе Ваш испытательный срок будет увеличен");
//                SendResponse response3 = bot.execute(messageText3);
//                return response3;
//            }
//        }
//
////      reportDate = reportRepository.getDateByChatId(petOwner.getTelegramId());
//        Report firstReport = reportRepository.getFirstReport(petOwner.getTelegramId());
//        probationPeriod = firstReport.getDate().plusDays(30);
//        Long numberOfRecordsInTable = reportRepository.getNumberOfRecords(petOwner.getTelegramId());
//        if ((probationPeriod.equals(todayDate) && numberOfRecordsInTable == 30) || numberOfRecordsInTable >= 30) {
//            SendMessage messageText4 = new SendMessage(chatId, "Поздравляем! " +
//                    "Вы успешно прошли испытательный срок.");
//            SendResponse response4 = bot.execute(messageText4);
//            return response4;
//        } else if (!todayDate.equals(probationPeriod)) {
//            SendMessage messageText5 = new SendMessage(petOwner.getTelegramId(), "К сожалению, " +
//                    "вы не прошли испытательный срок. Пожалуйста, следуйте инструкциям для дальнейших шагов.");
//            SendResponse response5 = bot.execute(messageText5);
//            return response5;
//        }
//
//        return null;
//    }
}

