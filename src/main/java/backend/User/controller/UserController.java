package backend.User.controller;

import backend.User.entity.dto.UserDto;
import backend.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // == 회원가입 ==
    @PostMapping("/signUp")
    public String signUp(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return "SignUp complete";
    }

    // == 로그인 ==
    @PostMapping("/logIn")
    public String logIn(@RequestBody UserDto userDto, HttpSession session) {

        UserDto loginResult = userService.login(userDto);

        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginName", loginResult.getUsername());
            return "login complete";
        } else {
            // 로그인 실패
            return "login Fail";
        }
    }

    // == 마이페이지(상세 회원정보 조회) ==
    @GetMapping("/myPage/{id}")
    public String findById(@PathVariable Long id, Model model) {

        UserDto myPageUser = userService.findById(id);
        model.addAttribute("user", myPageUser);

        if (myPageUser == null) {
            // 회원 정보가 존재하지 않음
            return "User does not exist";
        }

        return "My Data " + myPageUser.toString();
    }

    // == 회원 정보 수정 ==
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody UserDto userDto, HttpSession session) {

        UserDto updateUserDto = userService.findById(id);

        if (updateUserDto == null) {
            // 회원 정보가 존재하지 않음
            return "User does not exist";
        }

        if (!updateUserDto.getUsername().equals(session.getAttribute("loginName"))) {
            // 로그인한 사용자와 수정 대상 사용자가 다름
            return "Unauthorized user";
        }

        // 회원 정보 수정
        UserDto newUserDto = UserDto.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .build();
        newUserDto.setId(id);
        userService.update(newUserDto);

        return "Update complete";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();

        return "logout";
    }
}