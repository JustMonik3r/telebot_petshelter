package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.Report;

import java.util.List;

public interface ReportService {
    Report create(Report report);

    Report findById(Long id);

    Report update(Long id, Report report);

    void delete(Long id);

    List<Report> findAll();
}
