package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.service.PetOwnerServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/owner")
@Tag(name = "Усыновители животных", description = "CRUD-методы для работы с усыновителями животных")
public class PetOwnerController {
    public PetOwnerServiceImpl petOwnerService;

    public PetOwnerController (PetOwnerServiceImpl petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    @PostMapping
    @Operation(summary = "Добавить усыновителя животного")
    public ResponseEntity<PetOwner> addOwner(

            @RequestParam  @Parameter(description = "id усыновителя") long telegramId,
            @RequestParam(required = false) @Parameter(description = "Имя") String name,
            @RequestParam(required = false) @Parameter(description = "email") String email,
            @RequestParam(required = false) @Parameter(description = "Телефон") Long phoneNumber,
            @RequestParam(required = false) @Parameter(description = "Забрал животного или ещё нет") Boolean tookAnAnimal)
    {
        return ResponseEntity.ok(petOwnerService.addOwner(new PetOwner(telegramId, name, email, phoneNumber, tookAnAnimal)));

    }

    @PutMapping
    @Operation(summary = "Внести изменения в данные усыновителя")
    public ResponseEntity<PetOwner> updateOwnerInfo(
//            @Parameter(name = "Объект пользователя") @org.springframework.web.bind.annotation.RequestBody PetOwner petOwner) {
            @RequestParam @Parameter(description = "id усыновителя") long telegramId,
            @RequestParam(required = false) @Parameter(description = "Имя") String name,
            @RequestParam(required = false) @Parameter(description = "email") String email,
            @RequestParam(required = false) @Parameter(description = "Телефон") Long phoneNumber,
            @RequestParam(required = false) @Parameter(description = "Забрал животного или ещё нет") Boolean tookAnAnimal)
    {
                return ResponseEntity.ok(petOwnerService.updateOwner(new PetOwner(telegramId, name, email, phoneNumber, tookAnAnimal)));
//        return ResponseEntity.ok(petOwnerService.updateOwner(petOwner));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение усыновителя по id")
    public ResponseEntity<PetOwner> getOwnerById(@PathVariable long id) {
        try {
            petOwnerService.getOwnerById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(petOwnerService.getOwnerById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получение всех усыновителей")
    public ResponseEntity<List<PetOwner>> getAllOwners() {
        return ResponseEntity.ok(petOwnerService.getAllOwners());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление усыновителя по id")
    public ResponseEntity<Void> deleteOwner(@PathVariable long id) {
        try {
            petOwnerService.deleteOwner(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
