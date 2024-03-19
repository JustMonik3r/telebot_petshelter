package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.PetOwner;

import java.util.List;

public interface PetOwnerService {

    // создание и сохранение нового владельца
    PetOwner addOwner(PetOwner petOwner);

    // поиск владельца по ID
    PetOwner getOwnerById(Long id);

    boolean existsById(Long id);

    // получение списка всех владельцев
    List<PetOwner> getAllOwners();

    // обновление данных владельца
    PetOwner updateOwner(PetOwner petOwner);

    //удаление владельца по id
    void deleteOwner(Long id);

//    void registerUser(Update update);
//
//    boolean newUser(Update update);
}
