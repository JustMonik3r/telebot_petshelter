package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.service.ReportService;

@RestController
@RequestMapping("/reports")
@Tag(name = "Отчет", description = "CRUD-методы для работы с отчетом")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(summary = "Поиск отчета по id", description = "Поиск отчета по id")
    @GetMapping("/{id}")
    public Report getReport(@Parameter(description = "Id отчета") @PathVariable Long id) {
        return reportService.findById(id);
    }

    @Operation(summary = "Получение списка всех отчетов", description = "Получение списка всех отчетов")
    @GetMapping
    public Iterable<Report> getAllReports() {
        return reportService.findAll();
    }

    @Operation(summary = "Удаление отчета по id", description = "Удаление отчета по id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReport(@Parameter(description = "Id отчета") @PathVariable Long id) {
        reportService.delete(id);
        return ResponseEntity.ok().build();
    }
}
