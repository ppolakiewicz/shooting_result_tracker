package com.str.shootingresulttracker.usermanagment

import org.mapstruct.factory.Mappers
import spock.lang.Specification
import spock.lang.Subject

class UserDtoServiceTest extends Specification {

    @Subject
    private UserService service

    void setup() {
        service = new UserService(
                new UserRepositoryInMemory(),
                Mappers.getMapper(UserMapper.class)
        )
    }

    void 'when searching for user by email and user exists then that user should be returned'() {
        given: 'created user'
            def username = "email"
            service.create(username, "password")

        when:
            def result = service.findByUserName(username).getValue()

        then:
            result.isPresent()
            result.get().getUsername() == username
    }
}
