package backend.User.entity.dto;

import backend.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;

    public UserDto(String password) {
        this.password = password;
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }
}
