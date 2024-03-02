package pro.sky.telebotpetshelter.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.repository.ReportRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final PetOwnerRepository petOwnerRepository;

    public ReportServiceImpl(ReportRepository reportRepository, PetOwnerRepository petOwnerRepository) {
        this.reportRepository = reportRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    /**
     * Метод для сохранения отчета в БД
     * @param report отчет
     * @return сохраненный отчет
     */
    public Report create(Report report) {
        return reportRepository.save(report);
    }

    /**
     * Метод для получения отчета по id из БД
     * @param id идентификатор отчета
     * @return найденный отчет
     */
    public Report findById(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Отчет не найден"));
    }

    /**
     * Метод для обновления отчета в БД
     * @param id идентификатор отчета
     * @param report найденный отчет
     * @return обновленный отчет
     */
    public Report update(Long id, Report report) {
        return reportRepository.save(report);
    }

    /**
     * Метод для удаления отчета из БД по id
     * @param id идентификатор отчета
     */
    public void delete(Long id) {
        reportRepository.deleteById(id);
    }

    /**
     * Метод для получения всех отчетов из БД
     * @return список найденных отчетов
     */
    public Iterable<Report> findAll() {
        return reportRepository.findAll();
    }

    public byte[] photoDownload(Long id) {
        var report = reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Отчет не найден"));
        if (report.getPhoto() == null) {
            throw new EntityNotFoundException("Фото не найдено");
        }
        return report.getPhoto();
    }

    /**
     * Метод для получения всех отчета из БД по id владельца и дате
     * @return список найденных отчетов
     */
    public Collection<Report> findByPetOwnerIdAndDate(Long userId, LocalDateTime dateTime) {
        return reportRepository.findByPetOwnerIdAndDate(userId, dateTime);
    }
}
