package pro.sky.telebotpetshelter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telebotpetshelter.entity.DogShelter;
import pro.sky.telebotpetshelter.repository.DogShelterRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShelterServiceImpl_DogTest {
    @Mock
    private DogShelterRepository dogShelterRepository;

    @InjectMocks
    private ShelterServiceImpl_Dog shelterServiceImpl_dog;

    @Test
    void addShelter() {
        // Создаем объект приют для собак
        DogShelter dogShelter = new DogShelter(1L, "Шаркович"
                , "Простоквашино, 2 ", "Время работы: 9.00-18.00"
                , "Принимаем бездомных собак", "+7 999 000 00 02"
                , "Нельзя приносить взрывоопасные предметы");

        // Мокируем поведение метода save в репозитории
        Mockito.when(dogShelterRepository.save(dogShelter)).thenReturn(dogShelter);

        // Вызываем метод addShelter и сохраняем результат
        DogShelter createdDogShelter = shelterServiceImpl_dog.addShelter(dogShelter);

        // Проверяем, что метод save был вызван один раз
        Mockito.verify(dogShelterRepository, Mockito.times(1)).save(dogShelter);

        // Проверяем, что созданный приют для котов совпадает с возвращенным результатом
        assertEquals(dogShelter, createdDogShelter);
    }

    @Test
    void updateShelter() {
    }

    @Test
    void getShelterById() {
        // Создаем объект приют для собак
        DogShelter dogShelter = new DogShelter(1L, "Шаркович"
                , "Простоквашино, 2 ", "Время работы: 9.00-18.00"
                , "Принимаем бездомных собак", "+7 999 000 00 02"
                , "Нельзя приносить взрывоопасные предметы");
        //catShelter.setId(1L);

        // Мокируем поведение метода findById в репозитории
        Mockito.when(dogShelterRepository.findById(1L)).thenReturn(Optional.of(dogShelter));

        // Вызываем метод getShelterById
        DogShelter resultDog = shelterServiceImpl_dog.getShelterById(1L);

        // Проверяем, что метод findById был вызван один раз с аргументом 1L
        Mockito.verify(dogShelterRepository, Mockito.times(1)).findById(1L);

        // Проверяем, что возвращенный приют соответствует ожидаемому приюту
        Assertions.assertEquals(dogShelter, resultDog);
    }

    @Test
    void getShelterByName() {
    }

    @Test
    void getAll() {

        // Создаем объекты приюты для собак
        DogShelter dogShelter1 = new DogShelter(1L, "Шаркович1"
                , "Простоквашино, 2 ", "Время работы: 9.00-18.00"
                , "Принимаем бездомных собак", "+7 999 000 00 02"
                , "Нельзя приносить взрывоопасные предметы");
        DogShelter dogShelter2 = new DogShelter(1L, "Шаркович2"
                , "Простоквашино, 3 ", "Время работы: 8.30-17.30"
                , "Принимаем бездомных собак", "+7 999 000 00 03"
                , "Нельзя приносить взрывоопасные предметы");

        List<DogShelter> dogShelters = Arrays.asList(dogShelter1, dogShelter2);

        // Мокируем поведение метода findAll в репозитории
        Mockito.when(dogShelterRepository.findAll()).thenReturn(dogShelters);

        // Вызываем метод getAll и сохраняем результат
        List<DogShelter> allDogShelters = shelterServiceImpl_dog.getAll();

        // Проверяем, что метод findAll был вызван один раз
        Mockito.verify(dogShelterRepository, Mockito.times(1)).findAll();

        // Проверяем, что список приютов, возвращенный из сервиса, соответствует списку, который мы задали
        assertEquals(dogShelters, allDogShelters);
    }

    @Test
    void delShelter() {
        long shelterId = 1L;

        doNothing().when(dogShelterRepository).deleteById(shelterId);

        shelterServiceImpl_dog.deleteShelter(shelterId);

        verify(dogShelterRepository).deleteById(shelterId);
    }

    @Test
    void getInfo() {
        String expectedInfo = "Это собачий приют Шаркович";

        when(dogShelterRepository.getInfo()).thenReturn(expectedInfo);

        String actualInfo = shelterServiceImpl_dog.getInfo();

        assertEquals(expectedInfo, actualInfo);
        verify(dogShelterRepository).getInfo();
    }

//    @Test
//    void getAllCatShelterInfo() {
//    }

    @Test
    void getAllDogShelterInfo() {
        DogShelter expectedDogShelter = new DogShelter(1L, "Шаркович"
                , "Простоквашино, 2 ", "Время работы: 9.00-18.00"
                , "Принимаем бездомных котов", "+7 999 000 00 02"
                , "Нельзя приносить взрывоопасные предметы");

        when(dogShelterRepository.getAllInfo()).thenReturn(expectedDogShelter);

        DogShelter actualDogShelter = shelterServiceImpl_dog.getAllDogShelterInfo();

        assertEquals(expectedDogShelter, actualDogShelter);
        verify(dogShelterRepository).getAllInfo();
    }

    @Test
    void getLocation() {
        String expectedLocation = "Простоквашино, 2";

        when(dogShelterRepository.getLocation()).thenReturn(expectedLocation);

        String actualLocation = shelterServiceImpl_dog.getLocation();

        assertEquals(expectedLocation, actualLocation);
        verify(dogShelterRepository).getLocation();
    }

    @Test
    void getTimetable() {
        String expectedTimetable = "с 09.00 до 18.00";

        when(dogShelterRepository.getTimetable()).thenReturn(expectedTimetable);

        String actualTimetable = shelterServiceImpl_dog.getTimetable();

        assertEquals(expectedTimetable, actualTimetable);
        verify(dogShelterRepository).getTimetable();
    }

    @Test
    void getSecurity() {
        String expectedSecurity = "Круглосуточное наблюдение";

        when(dogShelterRepository.getSecurity()).thenReturn(expectedSecurity);

        String actualSecurity = shelterServiceImpl_dog.getSecurity();

        assertEquals(expectedSecurity, actualSecurity);
        verify(dogShelterRepository).getSecurity();
    }

    @Test
    void getSafetyAdvice() {
        String expectedSafetyAdvice = "Нельзя приносить взрывоопасные предметы";

        when(dogShelterRepository.getSafetyMeasures()).thenReturn(expectedSafetyAdvice);

        String actualSafetyAdvice = shelterServiceImpl_dog.getSafetyAdvice();

        assertEquals(expectedSafetyAdvice, actualSafetyAdvice);
        verify(dogShelterRepository).getSafetyMeasures();
    }
}