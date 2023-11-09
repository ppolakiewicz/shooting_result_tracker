package com.str.shootingresulttracker.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.regex.Pattern;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

class ULIDGeneratorTest {

    private ULIDGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new ULIDGenerator();
    }

    @Test
    @DisplayName("should create valid UUID value")
    void shouldCreateValidUUIDValue() {
        //when
        UUID uuid = (UUID) generator.generate(null, null, null, null);

        //then
        Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
        Assertions.assertTrue(UUID_REGEX.matcher(uuid.toString()).matches());
    }

    @Test
    @DisplayName("event type should be insert only")
    void eventTypeShouldBeInsertOnly() {
        //expect
        Assertions.assertEquals(INSERT_ONLY, generator.getEventTypes());
    }
}
