package pro.sky.telebotpetshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.Volunteer;
import pro.sky.telebotpetshelter.repository.VolunteerRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Метод для создания и сохранения объекта волонтера в БД
     * @param volunteer
     * @return
     */

    public Volunteer createVolunteer (Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }

    /**
     * Метод возвращает всех волонтеров из БД
     * @return список найденных волонтеров
     */
    public Collection<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }

    /**
     * Возвращает любого волонтера из списка волонтеров.
     *
     * @return объект типа Optional<Volunteer>, содержащий любого волонтера из списка волонтеров,
     * если список не пустой, иначе пустой объект Optional.
     */
    public Optional<Volunteer> findAnyVolunteer() {
        return findAll().stream()
                .findAny();
    }

    /**
     * Удаляет волонтера из БД
     * @param telegramId
     */
    public void deleteVolunteer(Long telegramId) {
        volunteerRepository.deleteById(telegramId);
    }
}