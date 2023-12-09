package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDtoServiceTest extends AbstractUnitTest {

    private UserService service;

    @BeforeEach
    void setup() {
        service = new UserService(
                new UserRepositoryInMemory(),
                Mappers.getMapper(UserMapper.class),
                clock
        );
    }

    @Test
    @DisplayName("when searching for user by email and user exists then that user should be returned")
    void whenSearchingForUserByEmailAndUserExistsThenThatUserShouldBeReturned() {
        //given: created user
        var username = "email";
        service.create(username, "password");

        //when
        var result = service.findByUserName(username);

        //then
        assertTrue(result.isValue());
        Assertions.assertEquals(username, result.getValue().getUsername());
    }
}
