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

    // добавить в репо
    @Query(value = "SELECT HandlerTips FROM dog_shelter", nativeQuery = true)
    String getHandlerTips();

    @Query(value = "SELECT RecForProvenHandlers FROM dog_shelter", nativeQuery = true)
    String getRecForProvenHandlers();

    @Query(value = "SELECT RulesForMeeting FROM dog_shelter", nativeQuery = true)
    String getRulesForMeeting();

    @Query(value = "SELECT DocumentList FROM dog_shelter", nativeQuery = true)
    String getDocumentList();

    @Query(value = "SELECT RecForTransport FROM dog_shelter", nativeQuery = true)
    String getRecForTransport();

    @Query(value = "SELECT HomeRecommendForSmallPet FROM dog_shelter", nativeQuery = true)
    String getHomeRecommendForSmallPet();

    @Query(value = "SELECT HomeRecommendForBigPet FROM dog_shelter", nativeQuery = true)
    String getHomeRecommendForBigPet();

    @Query(value = "SELECT HomeRecommendForDisable FROM dog_shelter", nativeQuery = true)
    String getHomeRecommendForDisable();

    @Query(value = "SELECT ReasonsForRefusal FROM dog_shelter", nativeQuery = true)
    String getReasonsForRefusal();
}
