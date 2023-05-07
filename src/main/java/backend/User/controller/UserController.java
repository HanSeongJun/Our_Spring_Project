package backend.User.controller;

import backend.User.entity.dto.UserDto;
import backend.User.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    // == 회원가입 ==
    @PostMapping("/signUp")
    public String signUp(@RequestBody UserDto userDto) {
        userService.save(userDto);
        log.info("UserController.signUp()");
        return "SignUp complete";
    }

    // == 로그인 ==
    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestBody UserDto userDto, HttpSession session) {

        UserDto loginResult = userService.login(userDto);
        log.info("UserController.logIn");
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginUser", loginResult);
            return ResponseEntity.ok("{\"message\": \"login complete\"}");
        } else {
            // 로그인 실패
            return ResponseEntity.badRequest().body("{\"message\": \"login fail\"}");
        }
    }

    // == 마이페이지(상세 회원정보 조회) ==
    @GetMapping("/myPage")
    public UserDto myPage(HttpSession session) {

        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        log.info("UserController.myPage()");
        log.info("loginUser = " + loginUser);

        if (loginUser == null) {
            // 로그인하지 않은 경우
            return null;
        }

        // 로그인한 유저 정보 반환
        return loginUser;
    }

    // == 회원정보 수정 ==
    @PutMapping("/update")
    public String update(@RequestBody UserDto userDto, HttpSession session) {

        UserDto loginUser = (UserDto) session.getAttribute("loginUser");

        // 회원 정보 수정
        UserDto newUserDto = UserDto.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .build();
        newUserDto.setId(loginUser.getId());
        userService.update(newUserDto);

        // 로그인한 사용자의 세션 정보 업데이트
        session.setAttribute("loginUser", newUserDto);
        log.info("UserController.update");

        return "Update complete";
    }

    // == 로그아웃 ==
    @PostMapping("/logOut")
    public ResponseEntity<String> logOut(HttpSession session) {

        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        log.info("UserController.logout");

        if (loginUser != null) {
            session.removeAttribute("loginUser");
            return ResponseEntity.ok("{\"message\": \"logout complete\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"user not logged in\"}");
        }
    }
}