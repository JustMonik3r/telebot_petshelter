package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.Volunteer;

import java.util.Collection;
import java.util.Optional;

public interface VolunteerService {
    /**
     * Метод для создания и сохранения объекта волонтера в БД
     * @param volunteer
     * @return
     */
    Volunteer createVolunteer (Volunteer volunteer);

    /**
     * Метод возвращает всех волонтеров из БД
     * @return список найденных волонтеров
     */
    Collection<Volunteer> findAll();

    /**
     * Возвращает любого волонтера из списка волонтеров.
     *
     * @return объект типа Optional<Volunteer>, содержащий любого волонтера из списка волонтеров,
     * если список не пустой, иначе пустой объект Optional.
     */
    Optional<Volunteer> findAnyVolunteer();

    /**
     * Удаляет волонтера из БД
     * @param telegramId
     */
    void deleteVolunteer(Long telegramId);
}
