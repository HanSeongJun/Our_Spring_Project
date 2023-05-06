package backend.repository.map;

import backend.entity.map.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityByCityName(String cityName);
}
