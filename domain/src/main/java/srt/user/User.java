package srt.user;

import java.util.UUID;

public record User(
        UUID id,
        String email,
        String password,
        Role role,
        boolean active) {

    public enum Role {
        ADMIN,
        USER
    }

    public static User newBasicUser(String email, String password) {
        return new User(
                UUID.randomUUID(),
                email,
                password,
                Role.USER,
                true);
    }

}
