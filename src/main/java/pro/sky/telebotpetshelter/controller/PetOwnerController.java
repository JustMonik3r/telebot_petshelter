package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.service.PetOwnerServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/owner")
public class PetOwnerController {
    public PetOwnerServiceImpl petOwnerService;

    public PetOwnerController (PetOwnerServiceImpl petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    @PostMapping
    public ResponseEntity<PetOwner> addOwner(@Parameter(name = "Объект пользователя") @RequestBody PetOwner petOwner) {
        return ResponseEntity.ok(petOwnerService.addOwner(petOwner));
    }

    @PutMapping
    public ResponseEntity<PetOwner> updateOwnerInfo(@Parameter(name = "Объект пользователя") @org.springframework.web.bind.annotation.RequestBody PetOwner petOwner) {
        return ResponseEntity.ok(petOwnerService.updateOwner(petOwner));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetOwner> getOwnerById(@PathVariable long id) {
        try {
            petOwnerService.getOwnerById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(petOwnerService.getOwnerById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PetOwner>> getAllOwners() {
        return ResponseEntity.ok(petOwnerService.getAllOwners());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable long id) {
        try {
            petOwnerService.deleteOwner(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
