package backend.User.controller;

import backend.User.entity.dto.UserDto;
import backend.User.service.UserService;
import backend.User.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
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

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        userService.save(userDto);
        log.info("UserController.signUp()");
        return ResponseEntity.ok("{\"message\": \"SignUp complete\"}");
    }

    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestBody UserDto userDto, HttpSession session) {
        UserDto loginResult = userService.login(userDto);
        log.info("UserController.logIn");
        if (loginResult != null) {
            session.setAttribute("loginUser", loginResult);
            return ResponseEntity.ok("{\"message\": \"login complete\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"login fail\"}");
        }
    }

    @GetMapping("/myPage")
    public ResponseEntity<UserDto> myPage(HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        log.info("UserController.myPage()");
        log.info("loginUser = " + loginUser);
        if (loginUser == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(loginUser);
    }

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
        session.setAttribute("loginUser", newUserDto);
        log.info("UserController.update");
        return ResponseEntity.ok("{\"message\": \"Update complete\"}");
    }

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

    @PostMapping("/emailConfirm")
    public ResponseEntity<String> emailConfirm(@RequestParam String email) throws Exception {
        if (!EmailValidator.getInstance().isValid(email)) {
            return ResponseEntity.badRequest().body("{\"message\": \"Invalid email format\"}");
        }
        if (emailService.isEmailExists(email)) {
            return ResponseEntity.badRequest().body("{\"message\": \"Email already exists\"}");
        }
        String confirm = emailService.sendSimpleMessage(email);
        return ResponseEntity.ok(confirm);
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
        boolean exists = emailService.isEmailExists(email);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);
        return ResponseEntity.ok(result);
    }
}