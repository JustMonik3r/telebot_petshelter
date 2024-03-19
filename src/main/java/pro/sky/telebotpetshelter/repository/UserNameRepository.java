package pro.sky.telebotpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telebotpetshelter.entity.UserName;

import java.util.Optional;
@Repository
public interface UserNameRepository extends JpaRepository<UserName, Long> {


    boolean existsById(long telegramId);


    Optional<UserName> findByTelegramId(Long telegramId);
}
