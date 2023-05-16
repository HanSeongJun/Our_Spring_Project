package backend.User.controller;

import backend.User.entity.dto.UserDto;
import backend.User.service.UserService;
import backend.User.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    // == 회원가입 ==
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        userService.save(userDto);
        log.info("UserController.signUp()");
        return ResponseEntity.ok("{\"message\": \"SignUp complete\"}");
    }

    // == 로그인 ==
    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestBody UserDto userDto, HttpSession session) {
        UserDto loginResult = userService.login(userDto);
        log.info("UserController.logIn()");

        if (loginResult != null) {
            session.setAttribute("loginUser", loginResult);
            return ResponseEntity.ok("{\"message\": \"login complete\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"login fail\"}");
        }
    }

    // == 마이페이지 ==
    @GetMapping("/myPage")
    public ResponseEntity<UserDto> myPage(HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");

        if (loginUser == null) {
            // 로그인하지 않은 경우
            return ResponseEntity.status(401).build();
        }

        log.info("UserController.myPage()");
        log.info("loginUser = " + loginUser);

        return ResponseEntity.ok(loginUser);
    }

    // == 회원정보 수정 ==
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDto userDto, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");

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

        log.info("UserController.update()");

        return ResponseEntity.ok("{\"message\": \"Update complete\"}");
    }

    // == 로그아웃 ==
    @PostMapping("/logOut")
    public ResponseEntity<String> logOut(HttpSession session) {

        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        log.info("UserController.logOut()");

        if (loginUser != null) {
            session.removeAttribute("loginUser");
            return ResponseEntity.ok("{\"message\": \"logout complete\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"user not logged in\"}");
        }
    }

    // == 이메일 인증 ==
    @PostMapping("/emailConfirm")
    public ResponseEntity<String> emailConfirm(@RequestParam String email) throws Exception {
        if (!EmailValidator.getInstance().isValid(email)) {
            return ResponseEntity.badRequest().body("{\"message\": \"Invalid email format\"}");
        }

        if (userService.isEmailExists(email)) {
            return ResponseEntity.badRequest().body("{\"message\": \"Email already exists\"}");
        }

        String confirm = emailService.sendSimpleMessage(email);

        return ResponseEntity.ok("{\"confirm\": \"" + confirm + "\"}");
    }

    // 아이디 찾기 이메일 인증
    @PostMapping("/findIdEmailConfirm")
    public ResponseEntity<String> findIdEmailConfirm(@RequestParam String email) throws Exception {
        String confirm = emailService.sendSimpleMessage(email);

        return ResponseEntity.ok("{\"confirm\": \"" + confirm + "\"}");
    }

    // 비밀번호 찾기 이메일 인증
    @PostMapping("/findPasswordEmailConfirm")
    public ResponseEntity<String> findPasswordEmailConfirm(@RequestParam String email) throws Exception {
        String confirm = emailService.sendSimpleMessage(email);

        return ResponseEntity.ok("{\"confirm\": \"" + confirm + "\"}");
    }

    // == 아이디 중복 검증 ==
    @GetMapping("/checkUserName")
    public ResponseEntity<Map<String, Boolean>> checkUserName(@RequestParam String username) {
        boolean exists = userService.isUserNameExists(username);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);

        return ResponseEntity.ok(result);
    }

    // == 닉네임 중복 검증 ==
    @GetMapping("/checkNickName")
    public ResponseEntity<Map<String, Boolean>> checkNickName(@RequestParam String nickname) {
        boolean exists = userService.isNickNameExists(nickname);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);

        return ResponseEntity.ok(result);
    }

    // == 이메일 중복 검증 ==
    @GetMapping("/checkEmail")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
        boolean exists = userService.isEmailExists(email);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/findUserId")
    public ResponseEntity<String> findUserIdByEmail(@RequestParam String email) {
        String userId = userService.findUserIdByEmail(email);

        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}