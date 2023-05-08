package backend.map.repository;

import backend.map.entity.ApiData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiDataRepository extends JpaRepository<ApiData,Long> {
}
