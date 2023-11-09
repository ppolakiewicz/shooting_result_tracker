package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import com.str.shootingresulttracker.domain.provider.MagazineTestProvider;
import com.str.shootingresulttracker.domain.provider.WeaponTestProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MagazineTest extends AbstractUnitTest {

    @Test
    @DisplayName("when magazine is created then creation date should be set according do provided clock")
    void whenMagazineIsCreatedThenCreationDateShouldBeSetAccordingDoProvidedClock() {
        //given
        var defaultTime = setClock(2018, 6, 2);

        //when
        Magazine magazine = new Magazine(
                "Test magazine",
                UUID.randomUUID(),
                clock
        );

        //then
        assertEquals(defaultTime, magazine.getCreationDate());
    }

    @Test
    @DisplayName("when magazine reaches his maximum capacity no new weapon can be added")
    void whenMagazineReachesHisMaximumCapacityNoNewWeaponCanBeAdded() {
        //given: new empty magazine
        Magazine magazine = new Magazine(
                "Test magazine",
                UUID.randomUUID(),
                clock
        );

        //and: magazine filled with weapons
        for (int i = 0; i < 50; i++) {
            magazine.addWeapon(WeaponTestProvider.create(clock));
        }

        //when: tried to add weapon to full magazine
        var result = magazine.addWeapon(WeaponTestProvider.create(clock));

        //then
        assertTrue(result.getError().isPresent());
    }

    @Test
    @DisplayName("when magazine do not contains ammunition that is added then new ammunition should be added to magazine")
    void whenMagazineDoNotContainsAmmunitionThatIsAddedThenNewAmmunitionShouldBeAddedToMagazine() {
        //given
        Magazine magazine = MagazineTestProvider.create(clock);
        Ammunition newAmmunition = new Ammunition(Caliber.ACP_45, 123);

        //when
        var result = magazine.addAmmunition(newAmmunition);

        //then
        assertTrue(result.getValue().isPresent());
        assertTrue(result.getValue().get());
        assertTrue(magazine.getAmmunition().contains(newAmmunition));
    }

    @Test
    @DisplayName("when magazine already contains ammunition for given caliber and same caliber ammunition is added then quantity should be summed")
    void whenMagazineAlreadyContainsAmmunitionForGivenCaliberAndSameCaliberAmmunitionIsAddedThenQuantityShouldBeSummed() {
        //given
        Caliber caliber = Caliber.ACP_45;
        Ammunition existingAmmunition = new Ammunition(caliber, 10);

        //and: magazine with ammunition for given caliber
        Magazine magazine = MagazineTestProvider.create(clock);
        magazine.addAmmunition(existingAmmunition);

        //and: new pack of ammunition
        Ammunition newPackOfAmmunition = new Ammunition(caliber, 25);

        //and: expected result
        Ammunition expectedAmmunition = new Ammunition(caliber, 35);

        //when
        var result = magazine.addAmmunition(newPackOfAmmunition);

        //then
        assertTrue(result.getValue().isPresent());
        assertTrue(result.getValue().get());
        assertTrue(magazine.getAmmunition().contains(expectedAmmunition));
    }

    @Test
    @DisplayName("when subtracting ammunition from magazine that do not exists in magazine then magazine should still do not contains this ammunition")
    void whenSubtractingAmmunitionFromMagazineThatDoNotExistsInMagazineThenMagazineShouldStillDoNotContainsThisAmmunition() {
        //given magazine with some ammunition
        Magazine magazine = MagazineTestProvider.create(clock);
        magazine.addAmmunition(new Ammunition(Caliber.ACP_45, 10));
        magazine.addAmmunition(new Ammunition(Caliber.ACP_380, 10));
        magazine.addAmmunition(new Ammunition(Caliber.PARABELLUM_9X19, 10));

        //and: ammunition that is not stored in magazine
        Caliber notStoredCaliber = Caliber.CASULL_454;
        Ammunition notStoredAmmunition = new Ammunition(notStoredCaliber, 44);

        //when
        var result = magazine.subtractAmmunition(notStoredAmmunition);

        //then
        assertTrue(result.getError().isEmpty());
        assertTrue(magazine.getAmmunition().stream().noneMatch(ammunition -> (ammunition.caliber() == notStoredCaliber)));
    }

    @Test
    @DisplayName("when subtracting ammunition from magazine then quantity should be subtracted")
    void whenSubtractingAmmunitionFromMagazineThenQuantityShouldBeSubtracted() {
        //given
        Caliber caliber = Caliber.ACP_45;
        Magazine magazine = MagazineTestProvider.create(clock);
        magazine.addAmmunition(new Ammunition(caliber, 10));

        //and
        Ammunition subtractedAmmunition = new Ammunition(caliber, 3);

        //and
        Ammunition expectedResult = new Ammunition(caliber, 7);

        //when
        var result = magazine.subtractAmmunition(subtractedAmmunition);

        //then
        assertTrue(result.getError().isEmpty());
        assertTrue(magazine.getAmmunition().contains(expectedResult));
    }

    @Test
    @DisplayName("when whole ammunition for given caliber is subtracted then magazine should not contain given caliber")
    void whenWholeAmmunitionForGivenCaliberIsSubtractedThenMagazineShouldNotContainGivenCaliber() {
        //given
        Caliber caliber = Caliber.ACP_45;
        Magazine magazine = MagazineTestProvider.create(clock);
        magazine.addAmmunition(new Ammunition(caliber, 10));

        //and
        Ammunition subtractedAmmunition = new Ammunition(caliber, 20);

        //when
        var result = magazine.subtractAmmunition(subtractedAmmunition);

        //then
        assertTrue(result.getError().isEmpty());
        assertTrue(magazine.getAmmunition().stream().noneMatch(ammunition -> ammunition.caliber() == caliber));
    }

    @Test
    @DisplayName("magazine name can be changed to non empty value")
    void magazineNameCanBeChangedToNonEmptyValue() {
        //given
        String newMagazineName = "New magazine name";
        Magazine magazine = MagazineTestProvider.create(clock);

        //expect
        Assertions.assertNotEquals(newMagazineName, magazine.getName());

        //when
        magazine.setName(newMagazineName);

        //then
        assertEquals(newMagazineName, magazine.getName());
    }

    @Test
    @DisplayName("magazine can not have empty name")
    void magazineCanNotHaveEmptyName() {
        //given
        String empty = "";
        UUID ownerId = UUID.randomUUID();

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Magazine(empty, ownerId, clock));
    }

}
