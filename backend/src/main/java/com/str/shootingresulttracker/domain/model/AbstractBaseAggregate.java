package com.str.shootingresulttracker.domain.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractBaseAggregate extends AbstractBaseDomainEntity {

    @Version
    private Long version;

    public AbstractBaseAggregate(Clock clock, UUID createdBy) {
        super(clock, createdBy);
        this.version = 1L;
    }
}
