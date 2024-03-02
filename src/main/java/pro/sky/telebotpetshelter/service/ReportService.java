package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.Report;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ReportService {
    Report create(Report report);

    Report findById(Long id);

    Report update(Long id, Report report);

    void delete(Long id);

    Iterable<Report> findAll();

    byte[] photoDownload(Long id);
    Collection<Report> findByPetOwnerIdAndDate(Long userId, LocalDateTime dateTime);
}
