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

    // add to repo
    @Query(value = "SELECT ReasonsForRefusal FROM cat_shelter", nativeQuery = true)
    String getReasonsForRefusal();

    @Query(value = "SELECT RecForProvenHandlers FROM cat_shelter", nativeQuery = true)
    String getRecForProvenHandlers();

    @Query(value = "SELECT HandlerTips FROM cat_shelter", nativeQuery = true)
    String getHandlerTips();

    @Query(value = "SELECT HomeRecommendForDisable FROM cat_shelter", nativeQuery = true)
    String getHomeRecommendForDisable();

    @Query(value = "SELECT HomeRecommendForBigPet FROM cat_shelter", nativeQuery = true)
    String getHomeRecommendForBigPet();

    @Query(value = "SELECT HomeRecommendForSmallPet FROM cat_shelter", nativeQuery = true)
    String getHomeRecommendForSmallPet();

    @Query(value = "SELECT RecForTransport FROM cat_shelter", nativeQuery = true)
    String getRecForTransport();

    @Query(value = "SELECT DocumentList FROM cat_shelter", nativeQuery = true)
    String getDocumentList();

    @Query(value = "SELECT RulesForMeeting FROM cat_shelter", nativeQuery = true)
    String getRulesForMeeting();
}
