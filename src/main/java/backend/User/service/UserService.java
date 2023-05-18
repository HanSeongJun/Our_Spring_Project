package backend.User.service;

import backend.User.entity.QUser;
import backend.User.entity.User;
import backend.User.entity.dto.UserDto;
import backend.User.repository.UserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EntityManager em;
    private final UserRepository userRepository;

    // == 회원가입 ==
    public Long save(UserDto userDto) {
        User savedUser = userRepository.save(User.toSaveEntity(userDto));
        return savedUser.getId();
    }

    // == 로그인 ==
    public UserDto login(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUsername())
                .filter(user -> user.getPassword().equals(userDto.getPassword()))
                .map(UserDto::toDto)
                .orElse(null);
    }

    // == 회원 정보 수정 ==
    public Long update(UserDto userDto) {
        return userRepository.findById(userDto.getId())
                .map(user -> {
                    user.setUsername(userDto.getUsername());
                    user.setNickname(userDto.getNickname());
                    user.setPassword(userDto.getPassword());
                    user.setEmail(userDto.getEmail());
                    userRepository.save(user);
                    return user.getId();
                })
                .orElse(null);
    }

    // == 아이디 중복 검증 ==
    public boolean isUserNameExists(String username) {
        QUser user = QUser.user;
        BooleanExpression UserNamePredicate = user.username.eq(username);
        long count = new JPAQueryFactory(em)
                .selectFrom(user)
                .where(UserNamePredicate)
                .fetchCount();
        return count > 0;
    }

    // == 닉네임 중복 검증 ==
    public boolean isNickNameExists(String nickname) {
        QUser user = QUser.user;
        BooleanExpression NickNamePredicate = user.nickname.eq(nickname);
        long count = new JPAQueryFactory(em)
                .selectFrom(user)
                .where(NickNamePredicate)
                .fetchCount();
        return count > 0;
    }

    // == 이메일 중복 검증 ==
    public boolean isEmailExists(String email) {
        QUser user = QUser.user;
        BooleanExpression emailPredicate = user.email.eq(email);
        long count = new JPAQueryFactory(em)
                .selectFrom(user)
                .where(emailPredicate)
                .fetchCount();
        return count > 0;
    }

    // 이메일 기반으로 아이디 찾기
    public String findUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getUsername();
        } else {
            return null;
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}