package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.Report;

public interface ReportService {
    Report findById(Long id);

    Iterable<Report> findAll();

    void delete(Long id);
}
