package pro.sky.telebotpetshelter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.service.CatService;

@RestController
@RequestMapping("/cats")
public class CatController {
private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    public Cat create(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam boolean isHealthy,
            @RequestParam long id) {
        return catService.create(new Cat(id, name, age, isHealthy));
    }
}
