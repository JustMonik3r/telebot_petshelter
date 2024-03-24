package pro.sky.telebotpetshelter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telebotpetshelter.entity.CatShelter;
import pro.sky.telebotpetshelter.repository.CatShelterRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShelterServiceImpl_CatTest {
    @Mock
    private CatShelterRepository catShelterRepository;

    @InjectMocks
    private ShelterServiceImpl_Cat shelterServiceImpl_cat;

    @Test
    void addShelter() {
        // Создаем объект приют для котов
        CatShelter catShelter = new CatShelter(1L, "Мурзильник"
                , "Простоквашино, 1 ", "Время работы: 8.00-17.00"
                , "Принимаем бездомных котов", "+7 999 000 00 01"
                , "Нельзя приносить взрывоопасные предметы");

        // Мокируем поведение метода save в репозитории
        Mockito.when(catShelterRepository.save(catShelter)).thenReturn(catShelter);

        // Вызываем метод addShelter и сохраняем результат
        CatShelter createdCatShelter = shelterServiceImpl_cat.addShelter(catShelter);

        // Проверяем, что метод save был вызван один раз
        Mockito.verify(catShelterRepository, Mockito.times(1)).save(catShelter);

        // Проверяем, что созданный приют для котов совпадает с возвращенным результатом
        assertEquals(catShelter, createdCatShelter);
    }

    @Test
    void updateShelter() {

    }

    @Test
    void getShelterById() {
        // Создаем объект приют для котов
        CatShelter catShelter = new CatShelter(1L, "Мурзильник"
                , "Простоквашино, 1 ", "Время работы: 8.00-17.00"
                , "Принимаем бездомных котов", "+7 999 000 00 01"
                , "Нельзя приносить взрывоопасные предметы");
        //catShelter.setId(1L);

        // Мокируем поведение метода findById в репозитории
        Mockito.when(catShelterRepository.findById(1L)).thenReturn(Optional.of(catShelter));

        // Вызываем метод getShelterById
        CatShelter resultCat = shelterServiceImpl_cat.getShelterById(1L);

        // Проверяем, что метод findById был вызван один раз с аргументом 1L
        Mockito.verify(catShelterRepository, Mockito.times(1)).findById(1L);

        // Проверяем, что возвращенный приют соответствует ожидаемому приюту
        Assertions.assertEquals(catShelter, resultCat);
    }

//    @Test
//    void getShelterByName() {
//        // Создаем объект приют для котов
//        CatShelter catShelter = new CatShelter(1L, "Мурзильник"
//                , "Простоквашино, 1 ", "Время работы: 8.00-17.00"
//                , "Принимаем бездомных котов", "+7 999 000 00 01"
//                , "Нельзя приносить взрывоопасные предметы");
//        //catShelter.setName("Мурзильник");
//
//        // Мокируем поведение метода findByName в репозитории
//        Mockito.when(catShelterRepository.findByName("Мурзильник")).thenReturn(Optional.of(catShelter));
//
//        // Вызываем метод getShelterByName
//        CatShelter resultCat = shelterServiceImpl_cat.getShelterByName("Мурзильник");
//
//        // Проверяем, что метод findByName был вызван один раз с аргументом Мурзильник
//        Mockito.verify(catShelterRepository, Mockito.times(1)).findByName("Мурзильник");
//
//        // Проверяем, что возвращенный приют соответствует ожидаемому приюту
//        Assertions.assertEquals(catShelter, resultCat);
//    }

    @Test
    void getAll() {

        // Создаем объекты приюты для котов
        CatShelter catShelter1 = new CatShelter(1L, "Мурзильник1"
                , "Простоквашино, 2 ", "Время работы: 8.00-17.00"
                , "Принимаем бездомных котов", "+7 999 000 00 02"
                , "Нельзя приносить взрывоопасные предметы");
        CatShelter catShelter2 = new CatShelter(1L, "Мурзильник2"
                , "Простоквашино, 1 ", "Время работы: 8.00-17.00"
                , "Принимаем бездомных котов", "+7 999 000 00 01"
                , "Нельзя приносить взрывоопасные предметы");

        List<CatShelter> catShelters = Arrays.asList(catShelter1, catShelter2);

        // Мокируем поведение метода findAll в репозитории
        Mockito.when(catShelterRepository.findAll()).thenReturn(catShelters);

        // Вызываем метод getAll и сохраняем результат
        List<CatShelter> allCatShelters = shelterServiceImpl_cat.getAll();

        // Проверяем, что метод findAll был вызван один раз
        Mockito.verify(catShelterRepository, Mockito.times(1)).findAll();

        // Проверяем, что список приютов, возвращенный из сервиса, соответствует списку, который мы задали
        assertEquals(catShelters, allCatShelters);
    }

    @Test
    void delShelter() {
        long shelterId = 1L;

        doNothing().when(catShelterRepository).deleteById(shelterId);

        shelterServiceImpl_cat.deleteShelter(shelterId);

        verify(catShelterRepository).deleteById(shelterId);
    }

    @Test
    void getInfo() {
        String expectedInfo = "Это кошачий приют Мурзильник";

        when(catShelterRepository.getInfo()).thenReturn(expectedInfo);

        String actualInfo = shelterServiceImpl_cat.getInfo();

        assertEquals(expectedInfo, actualInfo);
        verify(catShelterRepository).getInfo();
    }

    @Test
    void getAllCatShelterInfo() {
        CatShelter expectedCatShelter = new CatShelter(1L, "Мурзильник"
                , "Простоквашино, 1 ", "Время работы: 8.00-17.00"
                , "Принимаем бездомных котов", "+7 999 000 00 01"
                , "Нельзя приносить взрывоопасные предметы");

        when(catShelterRepository.getAllInfo()).thenReturn(expectedCatShelter);

        CatShelter actualCatShelter = shelterServiceImpl_cat.getAllCatShelterInfo();

        assertEquals(expectedCatShelter, actualCatShelter);
        verify(catShelterRepository).getAllInfo();
    }

//    @Test
//    void getAllDogShelterInfo() {
//    }

    @Test
    void getLocation() {
        String expectedLocation = "Простоквашино, 1";

        when(catShelterRepository.getLocation()).thenReturn(expectedLocation);

        String actualLocation = shelterServiceImpl_cat.getLocation();

        assertEquals(expectedLocation, actualLocation);
        verify(catShelterRepository).getLocation();
    }

    @Test
    void getTimetable() {
        String expectedTimetable = "с 08.00 до 17.00";

        when(catShelterRepository.getTimetable()).thenReturn(expectedTimetable);

        String actualTimetable = shelterServiceImpl_cat.getTimetable();

        assertEquals(expectedTimetable, actualTimetable);
        verify(catShelterRepository).getTimetable();
    }

    @Test
    void getSecurity() {
        String expectedSecurity = "Круглосуточное наблюдение";

        when(catShelterRepository.getSecurity()).thenReturn(expectedSecurity);

        String actualSecurity = shelterServiceImpl_cat.getSecurity();

        assertEquals(expectedSecurity, actualSecurity);
        verify(catShelterRepository).getSecurity();
    }

    @Test
    void getSafetyAdvice() {
        String expectedSafetyAdvice = "Нельзя приносить взрывоопасные предметы";

        when(catShelterRepository.getSafetyMeasures()).thenReturn(expectedSafetyAdvice);

        String actualSafetyAdvice = shelterServiceImpl_cat.getSafetyAdvice();

        assertEquals(expectedSafetyAdvice, actualSafetyAdvice);
        verify(catShelterRepository).getSafetyMeasures();
    }
}