package pro.sky.telebotpetshelter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telebotpetshelter.Utilities.Vaccinations;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.exceptions.NotFoundException;
import pro.sky.telebotpetshelter.repository.CatRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatServiceImplTest {

    @Mock
    private CatRepository catRepository;

    @InjectMocks
    private CatServiceImpl catServiceImpl;

    @Mock
    private Logger logger;

    @Test
    public void сreate() {
        // Создаем объект кота для теста
        Cat cat = new Cat(1L, "Tom", 3, true, Vaccinations.YES);

        // Мокируем поведение метода save в репозитории
        Mockito.when(catRepository.save(cat)).thenReturn(cat);

        // Вызываем метод create и сохраняем результат
        Cat createdCat = catServiceImpl.create(cat);

        // Проверяем, что метод save был вызван один раз
        Mockito.verify(catRepository, Mockito.times(1)).save(cat);

        // Проверяем, что созданный кот совпадает с возвращенным результатом
        assertEquals(cat, createdCat);
    }

    @Test
    public void getById() {
        // Создаем кота для теста
        Cat cat = new Cat(1L, "Tom", 3, true, Vaccinations.YES);
        cat.setId(1L);

        // Мокируем поведение метода findById в репозитории
        Mockito.when(catRepository.findById(1L)).thenReturn(Optional.of(cat));

        // Вызываем метод getById
        Cat resultCat = catServiceImpl.getById(1L);

        // Проверяем, что метод findById был вызван один раз с аргументом 1L
        Mockito.verify(catRepository, Mockito.times(1)).findById(1L);

        // Проверяем, что возвращенный кот соответствует ожидаемому коту
        Assertions.assertEquals(cat, resultCat);
    }

    @Test
    public void getByIdNotFound() {
        // Мокируем поведение метода findById в репозитории, чтобы вернуть Optional.empty()
        Mockito.when(catRepository.findById(2L)).thenReturn(Optional.empty());

        // Проверяем, что при передаче несуществующего id будет выброшено исключение NotFoundException
        Assertions.assertThrows(NotFoundException.class, () -> catServiceImpl.getById(2L));

        // Проверяем, что метод findById был вызван один раз с аргументом 2L
        Mockito.verify(catRepository, Mockito.times(1)).findById(2L);
    }

    @Test
    void update_ExistingCat_CatUpdated() {
        //Подготовка входных данных
        Cat existingCat = new Cat(1L, "Tom", 3, true, Vaccinations.YES);
        Cat updatedCat = new Cat(1L, "Tommy", 4, false, Vaccinations.YES);

        when(catRepository.findById(1L)).thenReturn(Optional.of(existingCat));
        when(catRepository.save(updatedCat)).thenReturn(updatedCat);

        //Подготовка ожидаемого результата
        Cat result = catServiceImpl.update(updatedCat);

        //Начало теста
        assertEquals(updatedCat, result);
        verify(catRepository).findById(1L);
        verify(catRepository).save(updatedCat);
    }

    @Test
    void update_NonExistingCat_NotFoundExceptionThrown() {
        //Начало теста
        Cat nonExistingCat = new Cat(1L, "Tom", 3, true, Vaccinations.YES);
        //Подготовка ожидаемого результата
        when(catRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Начало теста
        assertThrows(NotFoundException.class, () -> catServiceImpl.update(nonExistingCat));
        verify(catRepository).findById(anyLong());
        verify(catRepository, never()).save(any());
    }


    //    @Test
//    public void remove() {
//        // Создаем кота для теста
//        Cat cat = new Cat(1L, "Tom", 3, true, Vaccinations.YES);
//        cat.setId(1L);
//
//        // Мокируем поведение метода getById в сервисе
//        Mockito.when(catServiceImpl.getById(1L)).thenReturn(cat);
//
//        // Вызываем метод remove
//        catServiceImpl.remove(1L);
//
//        // Проверяем, что метод getById был вызван один раз с аргументом 1L
//        Mockito.verify(catServiceImpl, Mockito.times(1)).getById(1L);
//
//        // Проверяем, что метод deleteById был вызван один раз с аргументом 1L
//        Mockito.verify(catRepository, Mockito.times(1)).deleteById(1L);
//
////        // Проверяем, что логгер был вызван один раз с нужным сообщением
////        Mockito.verify(logger, Mockito.times(1)).info("Вызван метод remove(удаление кота)");
//    }
    @Test
    public void getAll() {
        // Создаем список котов для теста
        Cat cat1 = new Cat(1L, "Tom", 3, true, Vaccinations.YES);
        Cat cat2 = new Cat(1L, "Tommy", 4, false, Vaccinations.YES);
        List<Cat> cats = Arrays.asList(cat1, cat2);

        // Мокируем поведение метода findAll в репозитории
        Mockito.when(catRepository.findAll()).thenReturn(cats);

        // Вызываем метод getAll и сохраняем результат
        List<Cat> allCats = catServiceImpl.getAll();

        // Проверяем, что метод findAll был вызван один раз
        Mockito.verify(catRepository, Mockito.times(1)).findAll();

        // Проверяем, что список котов, возвращенный из сервиса, соответствует списку, который мы задали
        assertEquals(cats, allCats);
    }


}