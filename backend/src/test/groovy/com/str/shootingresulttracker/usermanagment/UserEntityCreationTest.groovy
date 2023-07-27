package com.str.shootingresulttracker.usermanagment

import spock.lang.Specification

class UserEntityCreationTest extends Specification {

    void 'when creating new basic user then user should have provided password and username, rule USER and be active'() {
        given:
            String password = 'pass'
            String username = 'email'

        when:
            UserEntity user = UserEntity.newBasicUser(username, password)

        then:
            user.getUsername() == username
            user.getPassword() == password
            user.getRole() == UserRole.USER
    }

}
