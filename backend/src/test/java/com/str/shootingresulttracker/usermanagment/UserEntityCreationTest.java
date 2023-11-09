package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.str.shootingresulttracker.usermanagment.UserPermission.MAGAZINE;
import static com.str.shootingresulttracker.usermanagment.UserPermission.WEAPON;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityCreationTest extends AbstractUnitTest {

    @Test
    @DisplayName("when creating new basic user then user should have provided password and username and basic permissions")
    void whenCreatingNewBasicUserThenUserShouldHaveProvidedPasswordAndUsernameAndBasicPermissions() {
        //given
        var password = "pass";
        var username = "email";

        //when
        UserEntity user = UserEntity.newBasicUser(username, password, clock);

        //then
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(List.of(MAGAZINE, WEAPON), user.getPermissions());
    }
}
