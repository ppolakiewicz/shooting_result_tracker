package srt.user

import spock.lang.Specification
import spock.lang.Subject

class UserServiceTest extends Specification {

    @Subject
    private UserService service

    void setup() {
        service = new UserService(new UserRepositoryInMemory())
    }

    void 'when searching for user by email and user exists then that user should be returned'() {
        given: 'created user'
            def userEmail = "email"
            service.create(userEmail, "password")

        when:
            def result = service.findByEmail(userEmail)

        then:
            result.containsValue()
            result.getValue().getEmail() == userEmail
    }
}
