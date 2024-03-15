package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.PetOwner;

import java.util.List;

public interface PetOwnerService {

    // создание и сохранение нового хозяина
    PetOwner createOwner(PetOwner petOwner);

    // поиск хозяина по ID
    PetOwner getOwnerById(Long id);

    boolean existsById(Long id);

    // получение списка всех хозяев
    List<PetOwner> getAllOwners();

    // обновление данных хозяина
    PetOwner updateOwner(PetOwner petOwner);

    //удаление хозяина по id
    void deleteOwner(Long id);

}
