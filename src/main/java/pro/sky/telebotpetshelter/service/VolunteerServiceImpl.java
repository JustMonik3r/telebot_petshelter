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

    public Volunteer createVolunteer (Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }

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
        return findVolunteers().stream()
                .findAny();
    }

    public void deleteVolunteer(Long telegramId) {
        volunteerRepository.deleteById(telegramId);
    }
}