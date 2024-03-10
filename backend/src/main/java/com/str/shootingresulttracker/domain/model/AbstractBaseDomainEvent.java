package com.str.shootingresulttracker.domain.model;

import lombok.Getter;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
public abstract class AbstractBaseDomainEvent {

    private final UUID eventId;
    private final OffsetDateTime creationDate;

    public AbstractBaseDomainEvent(Clock clock) {
        Objects.requireNonNull(clock, "Clock must be provided");
        this.eventId = UUID.randomUUID();
        this.creationDate = OffsetDateTime.now(clock);
    }
}
