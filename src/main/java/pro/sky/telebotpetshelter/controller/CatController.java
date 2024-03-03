package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.utils.Vaccinations;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.service.CatService;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    @Operation(summary = "Добавить кота в приют")
    public Cat create(
            @RequestParam @Parameter(description = "Имя кота") String name,
            @RequestParam @Parameter(description = "Возраст") int age,
            @RequestParam @Parameter(description = "Здоров?") boolean isHealthy,
            @RequestParam @Parameter(description = "Вакцинация?") Vaccinations vaccinations) {
        return catService.create(new Cat(name, age, isHealthy, vaccinations));
    }

    @GetMapping("/{cat_id}")
    @Operation(summary = "Получение кота по ID")
    public Cat getByCatId(@RequestParam @Parameter(description = "ID кота") Long id) {
        return catService.getById(id);
    }

    @PutMapping
    @Operation(summary = "Изменить информацию о коте")
    public Cat update(
            @RequestParam @Parameter(description = "ID кота") Long id,
            @RequestParam(required = false) @Parameter(description = "Имя кота") String name,
            @RequestParam(required = false) @Parameter(description = "Возраст кота") Integer age,
            @RequestParam(required = false) @Parameter(description = "Здоров?") Boolean isHealthy,
            @RequestParam(required = false) @Parameter(description = "Вакцинация?") Vaccinations vaccinations) {
        return catService.update(new Cat(id, name, age, isHealthy, vaccinations));
    }


    @DeleteMapping("/{cat_id}")
    @Operation(summary = "Удаление кота по id")
    public ResponseEntity<String> deleteById(@Parameter(description = "ID кота") Long id) {
        try {
            catService.remove(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Кота забрали");
    }

    @GetMapping
    @Operation(summary = "Получение всех котов")
    public ResponseEntity<List<Cat>> getAll() {
        return ResponseEntity.ok(catService.getAll());
    }
}
