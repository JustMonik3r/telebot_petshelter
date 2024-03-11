package pro.sky.telebotpetshelter.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.service.ReportService;
import pro.sky.telebotpetshelter.service.ReportServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
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
