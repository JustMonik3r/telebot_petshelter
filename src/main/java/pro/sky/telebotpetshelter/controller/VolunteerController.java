package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.Volunteer;
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
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer) {
        return volunteerService.createVolunteer(volunteer);
    }

    @Operation(summary = "Получение списка всех волонтеров", description = "Получение списка всех волонтеров")
    @GetMapping
    public Collection<Volunteer> findVolunteers() {
        return volunteerService.findVolunteers();
    }

    @Operation(summary = "Получение любого волонтера из списка", description = "Получение любого волонтера из списка")
    @GetMapping("/any")
    public Optional<Volunteer> findAnyVolunteer(){
        return volunteerService.findAnyVolunteer();
    }

    @Operation(summary = "Удаление волонтера по id", description = "Удаление волонтера по id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteVolunteer(@Parameter(description = "Id волонтера") @PathVariable Long telegramId) {
        volunteerService.deleteVolunteer(telegramId);
        return ResponseEntity.ok().build();
    }
}
