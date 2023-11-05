package com.str.shootingresulttracker.core;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


/**
 * Basic entity that provides id and creation date fields to any other entities.
 */
@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractBaseEntity {

    private static final String GENERATOR_NAME = "ulid-generator";

    @Id
    @Column(name = "id")
    //Generates ULID instead of UUID based on this article
    //https://www.cybertec-postgresql.com/en/unexpected-downsides-of-uuid-keys-in-postgresql/
    @GeneratedValue(generator = GENERATOR_NAME)
    @GenericGenerator(name = GENERATOR_NAME, type = ULIDGenerator.class)
    private UUID id;

    @NotNull
    @Column(name = "create_date")
    private OffsetDateTime creationDate;

    public AbstractBaseEntity(Clock clock) {
        Objects.requireNonNull(clock, "Entity needs clock to set creation date");
        this.creationDate = OffsetDateTime.now(clock);
    }

}
