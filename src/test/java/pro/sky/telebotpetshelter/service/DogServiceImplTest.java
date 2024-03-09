package pro.sky.telebotpetshelter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telebotpetshelter.Utilities.Vaccinations;
import pro.sky.telebotpetshelter.entity.animals.Dog;
import pro.sky.telebotpetshelter.exceptions.NotFoundException;
import pro.sky.telebotpetshelter.repository.DogRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceImplTest {
    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private DogServiceImpl dogServiceImpl;


    @Test
    void create() {
        // Создаем объект собака для теста
        Dog dog = new Dog(1L, "Шарик", 2, true, Vaccinations.YES);

        // Мокируем поведение метода save в репозитории
        Mockito.when(dogRepository.save(dog)).thenReturn(dog);

        // Вызываем метод create и сохраняем результат
        Dog createdDog = dogServiceImpl.create(dog);

        // Проверяем, что метод save был вызван один раз
        Mockito.verify(dogRepository, Mockito.times(1)).save(dog);

        // Проверяем, что созданная собака совпадает с возвращенным результатом
        assertEquals(dog, createdDog);

    }

    @Test
    void getById() {
        // Создаем собаку для теста
        Dog dog = new Dog(1L, "Шарик", 2, true, Vaccinations.YES);
        dog.setId(1L);

        // Мокируем поведение метода findById в репозитории
        Mockito.when(dogRepository.findById(1L)).thenReturn(Optional.of(dog));

        // Вызываем метод getById
        Dog resultDog = dogServiceImpl.getById(1L);

        // Проверяем, что метод findById был вызван один раз с аргументом 1L
        Mockito.verify(dogRepository, Mockito.times(1)).findById(1L);

        // Проверяем, что возвращенная собака соответствует ожидаемой собаке
        Assertions.assertEquals(dog, resultDog);
    }

    @Test
    public void getByIdNotFound() {
        // Мокируем поведение метода findById в репозитории, чтобы вернуть Optional.empty()
        Mockito.when(dogRepository.findById(2L)).thenReturn(Optional.empty());

        // Проверяем, что при передаче несуществующего id будет выброшено исключение NotFoundException
        Assertions.assertThrows(NotFoundException.class, () -> dogServiceImpl.getById(2L));

        // Проверяем, что метод findById был вызван один раз с аргументом 2L
        Mockito.verify(dogRepository, Mockito.times(1)).findById(2L);
    }

    @Test
    void update_ExistingDog_DogUpdated() {
        //Подготовка входных данных
        Dog existingDog = new Dog(1L, "Шарик", 2, true, Vaccinations.YES);
        Dog updatedDog = new Dog(1L, "Умка", 3, false, Vaccinations.YES);

        when(dogRepository.findById(1L)).thenReturn(Optional.of(existingDog));
        when(dogRepository.save(updatedDog)).thenReturn(updatedDog);

        //Подготовка ожидаемого результата
        Dog result = dogServiceImpl.update(updatedDog);

        //Начало теста
        assertEquals(updatedDog, result);
        verify(dogRepository).findById(1L);
        verify(dogRepository).save(updatedDog);

    }

    @Test
    void update_NonExistingCat_NotFoundExceptionThrown() {
        //Начало теста
        Dog nonExistingDog = new Dog(1L, "Шарик", 2, true, Vaccinations.YES);
        //Подготовка ожидаемого результата
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Начало теста
        assertThrows(NotFoundException.class, () -> dogServiceImpl.update(nonExistingDog));
        verify(dogRepository).findById(anyLong());
        verify(dogRepository, never()).save(any());
    }

    @Test
    void remove() {
    }

    @Test
    void getAll() {
        // Создаем список собак для теста
        Dog dog1 = new Dog(1L, "Шарик", 2, true, Vaccinations.YES);
        Dog dog2 = new Dog(1L, "Умка", 3, false, Vaccinations.YES);
        List<Dog> dogs = Arrays.asList(dog1, dog2);

        // Мокируем поведение метода findAll в репозитории
        Mockito.when(dogRepository.findAll()).thenReturn(dogs);

        // Вызываем метод getAll и сохраняем результат
        List<Dog> allDogs = dogServiceImpl.getAll();

        // Проверяем, что метод findAll был вызван один раз
        Mockito.verify(dogRepository, Mockito.times(1)).findAll();

        // Проверяем, что список собак, возвращенный из сервиса, соответствует списку, который мы задали
        assertEquals(dogs, allDogs);
    }


}