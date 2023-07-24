package srt.user

import spock.lang.Specification

class UserCreationTest extends Specification {

    void 'when creating new basic user then user should have id, rule USER and be active'() {
        given:
            String password = 'pass'
            String email = 'email'

        when:
            User user = User.newBasicUser(email, password)

        then:
            user.getEmail() == email
            user.getPassword() == password
            user.getRole() == User.Role.USER
            user.getId() != null
    }

}
