package com.str.shootingresulttracker.web.magazine;

import com.str.shootingresulttracker.core.AbstractIntegrationTest;
import com.str.shootingresulttracker.web.kernel.WebException;
import com.str.shootingresulttracker.web.provider.UserDtoProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MagazineControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MagazineController controller;

    @Test
    @DisplayName("User can create two magazines with different name")
    void userCanCreateTwoMagazinesWithDifferentName() {
        //given
        var user = UserDtoProvider.create();

        //then
        var firstMagazine = new MagazineCreateCommand("FirstMagazine");
        var firstMagazineResult = controller.create(firstMagazine, user);
        assertEquals(firstMagazineResult.name(), firstMagazine.name());

        var secondMagazine = new MagazineCreateCommand("SecondMagazine");
        var secondMagazineResult = controller.create(secondMagazine, user);
        assertEquals(secondMagazineResult.name(), secondMagazine.name());
    }

    @Test
    @DisplayName("User can not have more than two magazines")
    void userCanNotHaveMoreThanTwoMagazines() {
        //given: user already have two magazines
        var user = UserDtoProvider.create();

        var firstMagazine = new MagazineCreateCommand("FirstMagazine");
        controller.create(firstMagazine, user);

        var secondMagazine = new MagazineCreateCommand("SecondMagazine");
        controller.create(secondMagazine, user);

        //then: creation of third magazine should return error
        var thirdMagazine = new MagazineCreateCommand("SecondMagazine");
        assertThrows(WebException.class, () -> controller.create(thirdMagazine, user));

    }

    @Test
    @DisplayName("User can not create two magazines with same name")
    void userCanNotHaveTwoMagazinesWithSameName() {
        //given
        var user = UserDtoProvider.create();

        //then
        var firstMagazine = new MagazineCreateCommand("FirstMagazine");
        assertDoesNotThrow(() -> controller.create(firstMagazine, user));
        assertThrows(WebException.class, () -> controller.create(firstMagazine, user));
    }

}