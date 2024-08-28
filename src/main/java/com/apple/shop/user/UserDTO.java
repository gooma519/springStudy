package com.apple.shop.user;

public class UserDTO {
    public String username;
    public String displayName;
    public Long id;

    public UserDTO(String username, String displayName, Long id) {
        this.username = username;
        this.displayName = displayName;
        this.id = id;
    }
}
