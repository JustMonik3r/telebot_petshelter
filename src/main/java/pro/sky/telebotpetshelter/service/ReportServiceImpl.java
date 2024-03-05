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
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.repository.ReportRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final PetOwnerRepository petOwnerRepository;

    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportServiceImpl(ReportRepository reportRepository, PetOwnerRepository petOwnerRepository) {
        this.reportRepository = reportRepository;
        this.petOwnerRepository = petOwnerRepository;
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

   /* @Scheduled(cron = "0 0 12 * * ?")
    private SendResponse sendWarning() {
        LocalDate todayDate = LocalDate.now();
        LocalDate lastReportDate;
        LocalDate firstReportDate;
        LocalDate twoDaysFromLastReportDate;
        LocalDate probationPeriod;
        int additionalProbationPeriod = 14;

        for (PetOwner petOwner : petOwnerServiceImlp.getAll()) {
            if (petOwner.isTookAnimal()) {
                SendMessage messageText = new SendMessage(petOwner.getId(), "Пожалуйста, отправьте отчёт, " +
                        "отправьте одним сообщением фотографии и описание");
                SendResponse response = bot.execute(messageText);
                return response;
            }
            Report lastReport = ReportRepository.getLastReportSent(petOwner.getId());
            lastReportDate = lastReport.getDate();
//            reportDate = dailyReportRepository.getDateByChatId(animalAdopter.getId());
            twoDaysFromLastReportDate = lastReportDate.plusDays(2);


            if (twoDaysFromLastReportDate.equals(todayDate)) {
                SendMessage messageText1 = new SendMessage(petOwner.getId(), "Вы не отправляли " +
                        "отчёты уже более двух дней");
                SendResponse response1 = bot.execute(messageText1);
                return response1;
            }
//            reportDate = reportRepository.getDateByChatId(petOwner.getId());
            Report firstReport = reportRepository.getFirstReport(petOwner.getId());
            probationPeriod = firstReport.getDate().plusDays(30);
            Long numberOfRecordsInTable = reportRepository.getNumberOfRecords(petOwner.getId());
            if ((probationPeriod.equals(todayDate) && numberOfRecordsInTable == 30) || numberOfRecordsInTable >= 30) {
                SendMessage messageText2 = new SendMessage(petOwner.getId(), "Поздравляем! " +
                        "Вы успешно прошли испытательный срок.");
                SendResponse response2 = bot.execute(messageText2);
                return response2;
            } else if (!todayDate.equals(probationPeriod)) {
                SendMessage messageText3 = new SendMessage(petOwner.getId(), "К сожалению, " +
                        "вы не прошли испытательный срок. Пожалуйста, следуйте инструкциям для дальнейших шагов.");
                SendResponse response3 = bot.execute(messageText3);
                return response3;
            }*/
           // return null;
    }

