package pro.sky.telebotpetshelter.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.repository.ReportRepository;
import pro.sky.telebotpetshelter.service.ReportServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportRepository reportRepository;

    @SpyBean
    private ReportServiceImpl reportService;

    private Long report_id = 1L;
    private Long chat_id = 1l;
    private String photo = "Фото";
    private LocalDate date = LocalDate.now();
    private String reportTextUnderPhoto = "Cocтояние, самочувствие";

    private Report reportObject() {
        Report report = new Report();
        report.setId(report_id);
        report.setChatId(chat_id);
        report.setPhoto(photo);
        report.setDate(date);
        report.setReportTextUnderPhoto(reportTextUnderPhoto);
        return report;
    }
    public JSONObject reportJSON() {
        JSONObject reportJSON = new JSONObject();
        reportJSON.put("report_id", report_id);
        reportJSON.put("chat_id", chat_id);
        reportJSON.put("photo", photo);
        reportJSON.put("date", date);
        reportJSON.put("reportTextUnderPhoto", reportTextUnderPhoto);
    return reportJSON;
    }

    @Test
    public void getAllReportsTest() throws Exception {
        when(reportRepository.findAll()).thenReturn(new ArrayList<>(List.of(reportObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reports")
                        .content(reportJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getReportTest() throws Exception {
        when(reportRepository.findById(any(Long.class))).thenReturn(Optional.of(reportObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reports/1")
                        .content(reportJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chatId").value(chat_id))
                .andExpect(jsonPath("$.photo").value(photo))
                .andExpect(jsonPath("$.localDate").value(date))
                .andExpect(jsonPath("$.reportTextUnderPhoto").value(reportTextUnderPhoto));
    }

    @Test
    public void deleteReportTestThrowNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/report/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

