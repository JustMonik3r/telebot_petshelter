package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telebotpetshelter.entity.animals.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
