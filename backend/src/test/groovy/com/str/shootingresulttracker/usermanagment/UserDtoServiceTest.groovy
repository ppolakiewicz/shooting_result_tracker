package com.str.shootingresulttracker.usermanagment

import com.str.shootingresulttracker.core.AbstractUnitTest
import org.mapstruct.factory.Mappers
import spock.lang.Subject

class UserDtoServiceTest extends AbstractUnitTest {

    @Subject
    private UserService service

    void setup() {
        service = new UserService(
                new UserRepositoryInMemory(),
                Mappers.getMapper(UserMapper.class),
                clock
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
