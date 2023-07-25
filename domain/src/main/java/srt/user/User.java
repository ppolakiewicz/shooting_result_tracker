package srt.user;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {

    private final UUID id;
    private final String email;
    private final String password;
    private final UserRole role;
    private final boolean active;

    public static User newBasicUser(String email, String password) {
        return new User(
                email,
                password,
                UserRole.USER,
                true);
    }

    public User(String email, String password, UserRole role, boolean active) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

}
