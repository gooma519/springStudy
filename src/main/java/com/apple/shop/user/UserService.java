package com.apple.shop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void signup(Map formData) {
        Users user = new Users();
        String password = (String) formData.get("password");
        user.setDisplayName((String) formData.get("displayName"));
        user.setUsername((String) formData.get("username"));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
