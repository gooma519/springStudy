package com.apple.shop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam Map formData) {
        userService.signup(formData);
        return "redirect:/signup";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin.html";
    }

    @GetMapping("/mypage")
    public String mypage(Authentication auth) {
        if(auth != null) {
            return "mypage.html";
        }
        return "redirect:/signin";
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
