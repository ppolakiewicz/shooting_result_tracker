package com.str.shootingresulttracker.usermanagment

import com.str.shootingresulttracker.core.AbstractUnitTest

class UserEntityCreationTest extends AbstractUnitTest {

    void 'when creating new basic user then user should have provided password and username, rule USER and be active'() {
        given:
            String password = 'pass'
            String username = 'email'

        when:
            UserEntity user = UserEntity.newBasicUser(username, password, clock)

        then:
            user.getUsername() == username
            user.getPassword() == password
            user.getPermissions() == [UserPermission.MAGAZINE, UserPermission.WEAPON]
    }

}
