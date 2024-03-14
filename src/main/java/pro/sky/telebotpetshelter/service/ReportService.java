package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.Report;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ReportService {

    Report findById(Long id);

    Iterable<Report> findAll();

    void delete(Long id);
}
