package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telebotpetshelter.entity.Volunteer;
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
