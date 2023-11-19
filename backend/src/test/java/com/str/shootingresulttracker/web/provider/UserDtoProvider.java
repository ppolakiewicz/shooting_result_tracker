package com.str.shootingresulttracker.web.provider;

import com.str.shootingresulttracker.usermanagment.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import static com.str.shootingresulttracker.usermanagment.UserPermission.MAGAZINE;
import static com.str.shootingresulttracker.usermanagment.UserPermission.WEAPON;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDtoProvider {

    public static UserDto create() {
        return new UserDto(
                UUID.randomUUID(),
                "User",
                "Password",
                List.of(MAGAZINE, WEAPON),
                true
        );
    }

}
