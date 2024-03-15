package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telebotpetshelter.entity.Volunteer;

import java.util.Collection;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    //Collection<Volunteer> findByVolunteerTrue();
}
