package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.Report;

public interface ReportService {
    /**
     * Метод для получения отчета по id из БД
     * @param id идентификатор отчета
     * @return найденный отчет
     */
    Report findById(Long id);

    /**
     * Метод для получения всех отчетов из БД
     * @return список найденных отчетов
     */
    Iterable<Report> findAll();

    /**
     * Метод для удаления отчета из БД по id
     * @param id идентификатор отчета
     */
    void delete(Long id);
}
