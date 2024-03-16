package pro.sky.telebotpetshelter.service;

import pro.sky.telebotpetshelter.entity.Volunteer;

import java.util.Collection;
import java.util.Optional;

public interface VolunteerService {
    Volunteer createVolunteer (Volunteer volunteer);

    Collection<Volunteer> findAll();

    Optional<Volunteer> findAnyVolunteer();

    void deleteVolunteer(Long telegramId);
}
