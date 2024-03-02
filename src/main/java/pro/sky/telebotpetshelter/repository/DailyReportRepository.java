package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telebotpetshelter.entity.DailyReport;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {

    //Создаем репозиторий в БД для хранения полученных сообщений
    List<DailyReport> findAllByExecDate(LocalDateTime localDateTime);
}
