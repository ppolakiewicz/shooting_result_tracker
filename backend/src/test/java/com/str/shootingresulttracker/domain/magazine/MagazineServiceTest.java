package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import com.str.shootingresulttracker.domain.magazine.error.MaximumNumberOfMagazines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MagazineServiceTest extends AbstractUnitTest {

    private MagazineService service;

    @BeforeEach
    void setUp() {
        MagazineRepository repository = new MagazineInMemoryRepository();
        MagazineMapper mapper = Mappers.getMapper(MagazineMapper.class);

        service = new MagazineService(repository, mapper, clock);
    }

    @Test
    @DisplayName("User can create two magazines with different name")
    void userCanCreateTwoMagazinesWithDifferentName() {
        //given
        var userId = UUID.randomUUID();

        //then
        var firstMagazineResult = service.createMagazine("FirstMagazine", userId);
        assertTrue(firstMagazineResult.getValue().isPresent());
        assertTrue(firstMagazineResult.getError().isEmpty());

        var secondMagazineResult = service.createMagazine("SecondMagazine", userId);
        assertTrue(secondMagazineResult.getValue().isPresent());
        assertTrue(secondMagazineResult.getError().isEmpty());
    }

    @Test
    @DisplayName("User can not have more than two magazines")
    void userCanNotHaveMoreThanTwoMagazines() {
        //given: user already have two magazines
        var userId = UUID.randomUUID();
        service.createMagazine("First magazine", userId);
        service.createMagazine("Second magazine", userId);

        //when: creation of third magazine should return error
        var result = service.createMagazine("Third magazine", userId);

        //then
        assertTrue(result.getError().isPresent());
        assertEquals(result.getError().get().getClass(), MaximumNumberOfMagazines.class);
    }

    @Test
    @DisplayName("User can not create two magazines with same name")
    void userCanNotHaveTwoMagazinesWithSameName() {
        //given
        var userId = UUID.randomUUID();
        var magazineName = "TestMagazine";

        //then
        assertTrue(service.createMagazine(magazineName, userId).getError().isEmpty());
        assertFalse(service.createMagazine(magazineName, userId).getError().isEmpty());
    }


}