package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telebotpetshelter.entity.Report;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Создаем репозиторий в БД для хранения полученных отчетов
 */
@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    Collection<Report> findByPetOwnerIdAndDate (Long PetOwnerId, LocalDateTime dateTime);
}
