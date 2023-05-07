package backend.User.service;

import backend.User.entity.User;
import backend.User.entity.dto.UserDto;
import backend.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(UserDto userDto) {
        User savedUser = userRepository.save(User.toSaveEntity(userDto));
        return savedUser.getId();
    }

    public UserDto login(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUsername())
                .filter(user -> user.getPassword().equals(userDto.getPassword()))
                .map(UserDto::toDto)
                .orElse(null);
    }

    public Long update(UserDto userDto) {
        return userRepository.findById(userDto.getId())
                .map(user -> {
                    user.setUsername(userDto.getUsername());
                    user.setNickname(userDto.getNickname());
                    user.setEmail(userDto.getEmail());
                    userRepository.save(user);
                    return user.getId();
                })
                .orElse(null);
    }
}
