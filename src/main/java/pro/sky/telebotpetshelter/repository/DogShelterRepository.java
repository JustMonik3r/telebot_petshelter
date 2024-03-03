package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.telebotpetshelter.entity.DogShelter;

import java.util.Optional;

@Repository
public interface DogShelterRepository extends JpaRepository<DogShelter, Long> {

    Optional<DogShelter> findByName(String name);

    @Query(value = "SELECT about_shelter FROM dog_shelter", nativeQuery = true)
    String getInfo();

    @Query(value = "SELECT location FROM dog_shelter", nativeQuery = true)
    String getLocation();

    @Query(value = "SELECT safety_measures FROM dog_shelter", nativeQuery = true)
    String getSafetyMeasures();

    @Query(value = "SELECT timetable FROM dog_shelter", nativeQuery = true)
    String getTimetable();

    @Query(value = "SELECT security FROM dog_shelter", nativeQuery = true)
    String getSecurity();

    @Query(value = "SELECT * FROM dog_shelter", nativeQuery = true)
    DogShelter getAllInfo();
}
