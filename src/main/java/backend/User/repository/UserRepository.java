package backend.User.repository;

import backend.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 회원 id로 정보 조회
    Optional<User> findByUsername(String username);
}
