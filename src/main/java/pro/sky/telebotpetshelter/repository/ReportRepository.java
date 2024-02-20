package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telebotpetshelter.entity.Report;

/**
 * Создаем репозиторий в БД для хранения полученных отчетов
 */
public interface ReportRepository extends JpaRepository<Report,Long> {
}
