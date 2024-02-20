package pro.sky.telebotpetshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.repository.ReportRepository;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
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
        return reportRepository.findById(id).get();
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
    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}
