package com.apple.shop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

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

    public UserDTO getUser(Long id){
        Optional<Users> user = userRepository.findById(id);
        var result = user.get();
        return new UserDTO(result.getUsername(), result.getDisplayName(), result.getId());
    }
}
