package com.str.shootingresulttracker.core


import spock.lang.Specification

import java.util.regex.Pattern

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY

class ULIDGeneratorTest extends Specification {

    private ULIDGenerator generator

    void setup() {
        generator = new ULIDGenerator()
    }

    void 'should create valid UUID value'() {
        when:
            UUID uuid = generator.generate(null, null, null, null) as UUID

        then:
            Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}")
            UUID_REGEX.matcher(uuid.toString()).matches()
    }

    void 'event type should be insert only'() {
        expect:
            generator.getEventTypes() == INSERT_ONLY
    }
}
