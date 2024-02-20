package pro.sky.telebotpetshelter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.service.ReportService;
import pro.sky.telebotpetshelter.service.ReportServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService=reportService;
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.create(report);
    }

    @GetMapping("/{id}")
    public Report getReport(@PathVariable Long id) {
        return reportService.findById(id);
    }

    @PutMapping("/{id}")
    public Report updateReport(@PathVariable Long id, @RequestBody Report report){
        return reportService.update(id, report);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReport(@PathVariable Long id) {
        reportService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Report> findAllReports() {
        return reportService.findAll();
    }






}
