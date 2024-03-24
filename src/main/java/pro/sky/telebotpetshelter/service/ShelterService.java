package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.CatShelter;
import pro.sky.telebotpetshelter.entity.DogShelter;

import java.util.List;

public interface ShelterService<T, D> {

    //добавить приют в БД
    T addShelter(T shelter);

    //обновить данные приюта
    T updateShelter(T shelter);

    //найти приют по id
    T getShelterById(long id);

    //найти приют по имени
    T getShelterByName(String name);

    //выдать список приютов
    List<T> getAll();


    //удалить приют
    void deleteShelter(long index);


    CatShelter getAllCatShelterInfo();

    DogShelter getAllDogShelterInfo();

    String getContacts();
}
