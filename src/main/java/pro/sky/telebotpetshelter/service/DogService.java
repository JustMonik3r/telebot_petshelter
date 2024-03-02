package pro.sky.telebotpetshelter.service;


import pro.sky.telebotpetshelter.entity.animals.Dog;
import pro.sky.telebotpetshelter.exceptions.NotFoundException;


import java.util.List;

public interface DogService {
    /**
     * Создает новый объект собаку в базе данных.
     *
     * @param dog объект собаку, содержащий информацию о создаваемой записи
     * @return возвращает объект собаку с присвоенным идентификатором, сохраненный в базе данных
     */
    Dog create(Dog dog);

    /**
     * Возвращает объект собаку по его идентификатору.
     *
     * @param id идентификатор собаки в базе данных
     * @return возвращает объект собаки с указанным Id
     * @throws NotFoundException если собака с указанным идентификатором не найден в базе данных
     */
    Dog getById(Long id);

    /**
     * Обновляет информацию о собаке.
     *
     * @param dog собаки, содержащий обновленную информацию
     * @return объект собаки с обновленной информацией, сохраненный в базе данных
     * @throws NotFoundException если переданный объект собаки не найден в базе данных
     */
    Dog update(Dog dog);

    /**
     * Удаляет запись о собаке по указанному идентификатору из базы данных.
     *
     * @param id идентификатор собаки, который должен быть удален
     */
    void remove(Long id);

    /**
     * @return возвращает всех собак взятых в приют
     */
    List<Dog> getAll();

}
