package com.str.shootingresulttracker.domain.kernel;

import com.str.shootingresulttracker.core.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.util.Objects;
import java.util.UUID;

/**
 * Basic domain entity that provides id, creation date, created by fields for any other domain entites
 */
@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbstractBaseDomainEntity  extends AbstractBaseEntity {

    @NotNull
    @Column(name = "created_by")
    private UUID createdBy;

    public AbstractBaseDomainEntity(Clock clock, UUID createdBy) {
        super(clock);

        Objects.requireNonNull(createdBy, "Entity needs creator ID");
        this.createdBy = createdBy;
    }
}
