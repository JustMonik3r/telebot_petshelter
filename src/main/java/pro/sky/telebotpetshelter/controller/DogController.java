package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.utils.Vaccinations;

import pro.sky.telebotpetshelter.entity.animals.Dog;
import pro.sky.telebotpetshelter.service.DogService;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    @Operation(summary = "Добавить собаку в приют")
    public Dog create(
            @RequestParam @Parameter(description = "Имя собаки") String name,
            @RequestParam @Parameter(description = "Возраст собаки") int age,
            @RequestParam @Parameter(description = "Здоров?") boolean isHealthy,
            @RequestParam @Parameter(description = "Вакцинация?") Vaccinations vaccinations) {
        return dogService.create(new Dog(name, age, isHealthy, vaccinations));
    }

    @GetMapping("/{dog_id}")
    @Operation(summary = "Получение собаки по ID")
    public Dog getByCatId(@RequestParam @Parameter(description = "ID собаки") Long id) {
        return dogService.getById(id);
    }

    @PutMapping
    @Operation(summary = "Изменить информацию о собаке")
    public Dog update(
            @RequestParam @Parameter(description = "ID собаки") Long id,
            @RequestParam(required = false) @Parameter(description = "Имя собаки") String name,
            @RequestParam(required = false) @Parameter(description = "Возраст собаки") Integer age,
            @RequestParam(required = false) @Parameter(description = "Здоров?") Boolean isHealthy,
            @RequestParam(required = false) @Parameter(description = "Вакцинация?") Vaccinations vaccinations) {
        return dogService.update(new Dog(id, name, age, isHealthy, vaccinations));
    }


    @DeleteMapping("/{dog_id}")
    @Operation(summary = "Удаление собаки по id")
    public ResponseEntity<String> deleteById(@Parameter(description = "ID собаки") Long id) {
        try {
            dogService.remove(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Собаку забрали");
    }

    @GetMapping
    @Operation(summary = "Получение всех собак")
    public ResponseEntity<List<Dog>> getAll() {
        return ResponseEntity.ok(dogService.getAll());
    }
}
