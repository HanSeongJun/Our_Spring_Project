package backend.User.service;

import backend.User.entity.User;
import backend.User.entity.dto.UserDto;
import backend.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // == 회원가입 ==
    public Long save(UserDto userDto) {
        User savedUser = userRepository.save(User.toSaveEntity(userDto));
        Long savedId = savedUser.getId();
        return savedId;
    }

    // == 로그인 ==
    public UserDto login(UserDto userDto) {
        /**
         * LogIn.js에서 아이디, 비밀번호를 받아오고
         * DB에서 해당 아이디에 대한 정보를 가져와서
         * 입력받은 비밀번호와 DB에서 조회한 비밀번호의 일치여부를 판단하여
         * 일치하면 로그인, 일치하지 않으면 로그인 실패로 처리
         */
        Optional<User> byUsername = userRepository.findByUsername(userDto.getUsername());

        if (byUsername.isPresent()) {
            User user = byUsername.get();
            if (user.getPassword().equals(userDto.getPassword())) {
                return UserDto.toDto(user);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public UserDto findById(Long id) {

        Optional<User> optionalUserDto = userRepository.findById(id);

        if (optionalUserDto.isPresent()) {
            User user = optionalUserDto.get();
            return UserDto.toDto(user);
        } else {
            return null;
        }
    }

    // == 회원 정보 수정 ==
    public Long update(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDto.getUsername());
            user.setNickname(userDto.getNickname());
            user.setEmail(userDto.getEmail());
            userRepository.save(user);
            return user.getId();
        } else {
            return null;
        }
    }
}
