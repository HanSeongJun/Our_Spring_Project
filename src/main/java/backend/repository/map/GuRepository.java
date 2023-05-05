package backend.repository.map;

import backend.entity.map.Gu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuRepository extends JpaRepository<Gu, Long> {
    Optional<Gu> findByGuCode(String guCode);
}
