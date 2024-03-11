package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.telebotpetshelter.entity.Report;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Создаем репозиторий в БД для хранения полученных отчетов
 */
@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    @Query(value = "select sent_date from_report where chat_id=?", nativeQuery = true)
    LocalDate getDateByChatId(Long chatId);

    @Query(value = "select * from report where chat_id=? ORDER BY sent_date DESC LIMIT 1", nativeQuery = true)
    Report getLastReportSent(Long chatId);

    @Query(value = "select COUNT(*) AS record_count report where chat_id=?", nativeQuery = true)
    Long getNumberOfRecords(Long chatId);
//    @Query("select * from report WHERE id = (SELECT MIN(id) FROM report) and chat_id=?")

    @Query(value = "select * from report where chat_id=? ORDER BY sent_date LIMIT 1", nativeQuery = true)
    Report getFirstReport(Long chatId);
}