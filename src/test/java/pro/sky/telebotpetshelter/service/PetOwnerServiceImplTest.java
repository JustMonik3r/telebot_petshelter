package pro.sky.telebotpetshelter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.exceptions.OwnerNotFoundException;
import pro.sky.telebotpetshelter.repository.PetOwnerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetOwnerServiceImplTest {
    @Mock
    PetOwnerRepository petOwnerRepository;
    @InjectMocks
    PetOwnerServiceImpl petOwnerService;
    @Mock
    private Logger logger;

    @Test
    void createOwner() {
        // Создаем объект владелец для теста
        PetOwner petOwner = new PetOwner(1L, "Иванов", "Иван");

        // Мокируем поведение метода save в репозитории
        when(petOwnerRepository.save(petOwner)).thenReturn(petOwner);

        // Вызываем метод createOwner и сохраняем результат
        PetOwner createdOwner = petOwnerService.createOwner(petOwner);

        // Проверяем, что метод save был вызван один раз
        verify(petOwnerRepository, times(1)).save(petOwner);
        // Проверяем, что имя владельца совпадает с возвращенным именем
        assertEquals(petOwner.getFirstName(), createdOwner.getFirstName());

    }

    @Test
    void getOwnerById() {
        Long ownerId = 1L;
        PetOwner petOwner = new PetOwner(1L, "Иванов", "Иван");
        petOwner.setTelegramId(ownerId);
        when(petOwnerRepository.findByTelegramId(ownerId)).thenReturn(Optional.of(petOwner));

        PetOwner retrievedOwner = petOwnerService.getOwnerById(ownerId);

        verify(petOwnerRepository, times(1)).findByTelegramId(ownerId);
        assertEquals(ownerId, retrievedOwner.getTelegramId());
        assertEquals(petOwner.getFirstName(), retrievedOwner.getFirstName());
    }

    @Test
    public void testGetOwnerById_OwnerNotFound() {

        Long ownerId = 2L;
        when(petOwnerRepository.findByTelegramId(ownerId)).thenReturn(Optional.empty());

        assertThrows(OwnerNotFoundException.class, () -> petOwnerService.getOwnerById(ownerId));
    }

    @Test
    void getAllOwners() {
        List<PetOwner> owners = new ArrayList<>();
        owners.add(new PetOwner(1L, "Иванов", "Иван"));
        owners.add(new PetOwner(2L, "Петров", "Петр"));
        when(petOwnerRepository.findAll()).thenReturn(owners);

        List<PetOwner> retrievedOwners = petOwnerService.getAllOwners();

        verify(petOwnerRepository, times(1)).findAll();
        assertEquals(2, retrievedOwners.size());
        assertEquals("Иванов", retrievedOwners.get(0).getFirstName());
        assertEquals("Петров", retrievedOwners.get(1).getFirstName());
    }
    @Test
    public void testGetAllOwners_EmptyList() {
        // Создаем пустой список
        List<PetOwner> owners = new ArrayList<>();
        when(petOwnerRepository.findAll()).thenReturn(owners);

        List<PetOwner> retrievedOwners = petOwnerService.getAllOwners();

        //Проверяем, что метод корректно обрабатывает случай, когда список владельцев пустой.
        verify(petOwnerRepository, times(1)).findAll();
        assertTrue(retrievedOwners.isEmpty());
    }

// Nik редактировал метод updateOwner. Без этого не работал тест
    @Test
    void updateOwner() {
        // Arrange
        PetOwner existingOwner = new PetOwner(1L, "Иванов", "Иван");
        PetOwner updatedOwner = new PetOwner(1L, "Сидоров", "Сидор");

        when(petOwnerRepository.findById(1L)).thenReturn(Optional.of(existingOwner));
        when(petOwnerRepository.save(updatedOwner)).thenReturn(updatedOwner);


        // Act
        PetOwner result = petOwnerService.updateOwner(updatedOwner);

        // Assert
        //verify(logger, times(1)).info("Был вызван метод updateOwner");
        verify(petOwnerRepository, times(1)).findById(1L);
        verify(petOwnerRepository, times(1)).save(existingOwner);
        assertEquals("Сидоров", result.getFirstName());
    }
    // Nik редактировал метод updateOwner. Без этого не работал тест
    @Test
    public void testUpdateOwner_NonExistentOwner() {
        // Arrange
        PetOwner updatedOwner = new PetOwner(1L, "Иванов", "Иван");
        when(petOwnerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OwnerNotFoundException.class, () -> petOwnerService.updateOwner(updatedOwner));
        verify(logger, times(0)).info("Был вызван метод updateOwner");
        verify(petOwnerRepository, times(0)).save(any());
    }

    @Test
    void deleteOwner() {
        // Arrange
        Long ownerId = 1L;

        // Act
        petOwnerService.deleteOwner(ownerId);

        // Assert
        //verify(logger, times(1)).info("Был вызван метод deleteOwner");
        verify(petOwnerRepository, times(1)).deleteById(ownerId);
    }
}