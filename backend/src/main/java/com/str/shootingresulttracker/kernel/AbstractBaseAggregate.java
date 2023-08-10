package com.str.shootingresulttracker.kernel;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Clock;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbstractBaseAggregate extends AbstractBaseEntity {

    @Version
    private Long version;

    public AbstractBaseAggregate(Clock clock) {
        super(clock);
    }
}
