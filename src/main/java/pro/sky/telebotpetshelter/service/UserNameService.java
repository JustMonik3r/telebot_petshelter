package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.model.Update;
import pro.sky.telebotpetshelter.entity.UserName;

import java.util.List;

public interface UserNameService {
    // Создание и сохранение пользователя в бд

    UserName create(UserName user);

    // Получение пользователя по id

    UserName getById(Long id);

    // Получение выбранного в боте приюта по id пользователя

    // String getShelterById(Long id);

    //return Список всех пользователей
    List<UserName> getAll();

    //Изменение пользователя
    UserName update(UserName user);
    //Удаление пользователя по id
    void deleteUser(Long id);

    void registerUser(Update update);

    boolean newUser(Update update);
}
