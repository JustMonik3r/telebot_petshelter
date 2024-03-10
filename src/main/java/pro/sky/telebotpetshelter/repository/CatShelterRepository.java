package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.telebotpetshelter.entity.CatShelter;

import java.util.Optional;

@Repository
public interface CatShelterRepository extends JpaRepository<CatShelter, Long> {

    Optional<CatShelter> findByName(String name);

    @Query(value = "SELECT about_shelter FROM cat_shelter", nativeQuery = true)
    String getInfo();

    @Query(value = "SELECT location FROM cat_shelter", nativeQuery = true)
    String getLocation();

    @Query(value = "SELECT safety_measures FROM cat_shelter", nativeQuery = true)
    String getSafetyMeasures();

    @Query(value = "SELECT timetable FROM cat_shelter", nativeQuery = true)
    String getTimetable();

    @Query(value = "SELECT security FROM cat_shelter", nativeQuery = true)
    String getSecurity();

    @Query(value = "SELECT * FROM cat_shelter", nativeQuery = true)
    CatShelter getAllInfo();
}
