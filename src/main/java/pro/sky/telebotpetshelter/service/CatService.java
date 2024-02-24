package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.esception.NotFoundException;

import java.util.List;

public interface CatService {
    /**
     * Создает новый объект кота в базе данных.
     *
     * @param cat объект кота, содержащий информацию о создаваемой записи
     * @return возвращает объект кота с присвоенным идентификатором, сохраненный в базе данных
     */
    Cat create(Cat cat);

    /**
     * Возвращает объект кота по его идентификатору.
     *
     * @param id идентификатор кота в базе данных
     * @return возвращает объект кота с указанным Id
     * @throws NotFoundException если кот с указанным идентификатором не найден в базе данных
     */
    Cat getById(Long id);

    /**
     * Обновляет информацию о коте.
     *
     * @param cat кота, содержащий обновленную информацию
     * @return объект кота с обновленной информацией, сохраненный в базе данных
     * @throws NotFoundException если переданный объект кота не найден в базе данных
     */
    Cat update(Cat cat);

    /**
     * Удаляет запись о коте по указанному идентификатору из базы данных.
     *
     * @param id идентификатор кота, который должен быть удален
     */
    void remove(Long id);

    /**
     * @return возвращает всех котов взятых в приют
     */
    List<Cat> getAll();

}
