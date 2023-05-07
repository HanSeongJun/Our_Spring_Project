package backend.map.repository;

import backend.map.entity.City;
import backend.map.entity.Gu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuRepository extends JpaRepository<Gu, Long> {
    Optional<Gu> findByGuCode(String guCode);
    List<Gu> findGuByCity(City city);
}
