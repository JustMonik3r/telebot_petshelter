package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.service.ReportService;
import pro.sky.telebotpetshelter.service.ReportServiceImpl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(summary = "Создание отчета", description = "Создание отчета")
    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.create(report);
    }

    @Operation(summary = "Поиск отчета по id", description = "Поиск отчета по id")
    @GetMapping("/{id}")
    public Report getReport(@Parameter(description = "Id отчета") @PathVariable Long id) {
        return reportService.findById(id);
    }

    @Operation(summary = "Изменение отчета", description = "Изменение отчета")
    @PutMapping("/{id}")
    public Report updateReport(@Parameter(description = "Id отчета") @PathVariable Long id, @RequestBody Report report) {
        return reportService.update(id, report);
    }

    @Operation(summary = "Удаление отчета по id", description = "Удаление отчета по id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReport(@Parameter(description = "Id отчета") @PathVariable Long id) {
        reportService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение списка всех отчетов", description = "Получение списка всех отчетов")
    @GetMapping
    public Iterable<Report> getAllReports() {
        return reportService.findAll();
    }

    @Operation(summary = "Выгрузка фото отчета")
    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> photoDownload(@PathVariable("id") Long id) {
        var photo = reportService.photoDownload(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(photo.length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(photo);
    }

    @GetMapping
    public Collection<Report> findByPetOwnerIdAndDate(@PathVariable Long id, @PathVariable LocalDateTime date){
        return reportService.findByPetOwnerIdAndDate(id, date);
    }
}
