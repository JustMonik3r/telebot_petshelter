package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telebotpetshelter.entity.InfoHowToTakeAnimal;

public interface InfoHowToTakeAnimalRepository extends JpaRepository<InfoHowToTakeAnimal, Long> {
    @Query(value = "SELECT * FROM take_animal_info", nativeQuery = true)
    public InfoHowToTakeAnimal getALlInfo();
}
