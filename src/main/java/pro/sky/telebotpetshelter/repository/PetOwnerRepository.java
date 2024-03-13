package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telebotpetshelter.entity.PetOwner;

import java.util.Optional;

@Repository

public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {

    boolean existsById(long telegramId);
    Optional<PetOwner> findByTelegramId(Long telegramId);
}
