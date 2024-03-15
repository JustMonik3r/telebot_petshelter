package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.Report;
import pro.sky.telebotpetshelter.entity.Volunteer;
import pro.sky.telebotpetshelter.service.ReportService;
import pro.sky.telebotpetshelter.service.VolunteerService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Operation(summary = "Создание нового волонтера", description = "Создание нового волонтера")
    @PostMapping
    public Volunteer createVolunteer (
        @RequestParam @Parameter(description = "Telegram ID волонтера") Long telegramId,
        @RequestParam @Parameter(description = "Имя волонтера") String firstName,
        @RequestParam @Parameter(description = "Фамилия волонтера") String lastName){
    return volunteerService.createVolunteer(new Volunteer(telegramId, firstName, lastName));
    }

    @Operation(summary = "Получение списка всех волонтеров", description = "Получение списка всех волонтеров")
    @GetMapping
    public Collection<Volunteer> findAll() {
        return volunteerService.findAll();
    }

    @Operation(summary = "Удаление волонтера по id", description = "Удаление волонтера по id")
    @DeleteMapping("/{telegramId}")
    public ResponseEntity  deleteVolunteer(@Parameter(description = "Id волонтера") @PathVariable Long telegramId) {
        volunteerService.deleteVolunteer(telegramId);
        return ResponseEntity.ok().build();
    }
}
