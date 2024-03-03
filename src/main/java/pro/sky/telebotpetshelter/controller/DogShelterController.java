package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.DogShelter;
import pro.sky.telebotpetshelter.entity.animals.Dog;
import pro.sky.telebotpetshelter.service.ShelterService;

import java.util.List;

@RestController
@RequestMapping("/dogs/shelters")
@Tag(name = "Собачий приют", description = "CRUD-методы для работы с приютом")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Выполняем запрос."),
        @ApiResponse(responseCode = "400", description = "Допущена ошибка в запросе."),
        @ApiResponse(responseCode = "404", description = "Неверный URL."),
        @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка сервера.")
})
public class DogShelterController {

    private final ShelterService<DogShelter, Dog> dogShelterService;

    public DogShelterController(ShelterService<DogShelter, Dog> dogShelterService) {
        this.dogShelterService = dogShelterService;
    }


    @PostMapping()
    public DogShelter create(@RequestParam @Parameter(description = "Название приюта") String name,
                             @RequestParam @Parameter(description = "Адрес приюта") String location,
                             @RequestParam @Parameter(description = "График работы приюта") String timetable,
                             @RequestParam @Parameter(description = "Информация о приюте") String about_shelter,
                             @RequestParam @Parameter(description = "Контакты охраны приюта") String security,
                             @RequestParam @Parameter(description = "Техника безопасности во время нахождения на территории приюта") String safetyMeasures
    ) {
        return dogShelterService.addShelter(new DogShelter(name, location, timetable, about_shelter, security, safetyMeasures));
    }

    @PutMapping()
    public DogShelter update(@RequestParam @Parameter(description = "id приюта") long id,
                             @RequestParam @Parameter(description = "Название приюта") String name,
                             @RequestParam @Parameter(description = "Адрес приюта") String location,
                             @RequestParam @Parameter(description = "График работы приюта") String timetable,
                             @RequestParam @Parameter(description = "Информация о приюте") String about_shelter,
                             @RequestParam @Parameter(description = "Контакты охраны приюта") String security,
                             @RequestParam @Parameter(description = "Техника безопасности во время нахождения на территории приюта") String safetyMeasures) {

        return dogShelterService.updateShelter(new DogShelter(id, name, location, timetable, about_shelter, security, safetyMeasures));
    }

    @GetMapping()
    public List<DogShelter> getAll() {
        return dogShelterService.getAll();
    }

    @GetMapping("/{id}")
    public DogShelter getShelterId(@PathVariable @Parameter(description = "id приюта") long id) {
        return (dogShelterService.getShelterById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "id приюта") long id) {
        try {
            dogShelterService.deleteShelter(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
