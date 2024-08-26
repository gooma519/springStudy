package com.apple.shop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signup(Map formData) {
        Users user = new Users();
        var encoder = new BCryptPasswordEncoder();
        String password = (String) formData.get("password");
        user.setDisplayName((String) formData.get("displayName"));
        user.setUsername((String) formData.get("username"));
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }
}
