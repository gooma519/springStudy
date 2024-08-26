package com.apple.shop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    String signup() {
        return "signup.html";
    }

    @PostMapping("/signup")
    String signup(@RequestParam Map formData) {
        System.out.println(formData);
        userService.signup(formData);
        return "redirect:/signup";
    }
}
