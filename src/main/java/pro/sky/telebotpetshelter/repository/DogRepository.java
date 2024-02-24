package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telebotpetshelter.entity.animals.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}
