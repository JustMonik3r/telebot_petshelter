package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.CatShelter;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.service.ShelterService;

import java.util.List;

@RestController
@RequestMapping("/cats/shelters")
@Tag(name = "Кошачий приют", description = "CRUD-методы для работы с приютом")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Выполняем запрос."),
        @ApiResponse(responseCode = "400", description = "Допущена ошибка в запросе."),
        @ApiResponse(responseCode = "404", description = "Неверный URL."),
        @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка сервера.")
})
public class CatShelterController {

    private final ShelterService<CatShelter, Cat> catShelterService;

    public CatShelterController(ShelterService<CatShelter, Cat> catShelterService) {
        this.catShelterService = catShelterService;
    }


    @PostMapping()
    @Operation(summary = "Добавить приют для котов")
    public CatShelter create(@RequestParam @Parameter(description = "Название приюта") String name,
                             @RequestParam @Parameter(description = "Адрес приюта") String location,
                             @RequestParam @Parameter(description = "График работы приюта") String timetable,
                             @RequestParam @Parameter(description = "Информация о приюте") String about_shelter,
                             @RequestParam @Parameter(description = "Контакты охраны приюта") String security,
                             @RequestParam @Parameter(description = "Техника безопасности во время нахождения на территории приюта") String safetyMeasures
    ) {
        return catShelterService.addShelter(new CatShelter(name, location, timetable, about_shelter, security, safetyMeasures));
    }

    @PutMapping()
    @Operation(summary = "Изменить информацию о приюте для котов")
    public CatShelter update(@RequestParam @Parameter(description = "id приюта") long id,
                             @RequestParam @Parameter(description = "Название приюта") String name,
                             @RequestParam @Parameter(description = "Адрес приюта") String location,
                             @RequestParam @Parameter(description = "График работы приюта") String timetable,
                             @RequestParam @Parameter(description = "Информация о приюте") String about_shelter,
                             @RequestParam @Parameter(description = "Контакты охраны приюта") String security,
                             @RequestParam @Parameter(description = "Техника безопасности во время нахождения на территории приюта") String safetyMeasures) {

        return catShelterService.updateShelter(new CatShelter(id, name, location, timetable, about_shelter, security, safetyMeasures));
    }

    @GetMapping("/all")
    @Operation(summary = "Получение всех приютов для котов")
    public List<CatShelter> getAll() {
        return catShelterService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение приюта для котов по id")
    public CatShelter getShelterId(@PathVariable @Parameter(description = "id приюта") long id) {
        return (catShelterService.getShelterById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление приюта для котов по id")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "id приюта") long id) {
        try {
            catShelterService.deleteShelter(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
