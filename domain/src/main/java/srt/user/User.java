package srt.user;

import java.util.UUID;

public class User {

    public enum Role {
        ADMIN,
        USER
    }

    private final UUID id;
    private final String email;
    private final String password;
    private final Role role;
    private final boolean active;

    public static User newBasicUser(String email, String password) {
        return new User(
                UUID.randomUUID(),
                email,
                password,
                Role.USER,
                true);
    }

    public User(UUID id, String email, String password, Role role, boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}
