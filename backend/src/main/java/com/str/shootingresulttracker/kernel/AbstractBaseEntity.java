package com.str.shootingresulttracker.kernel;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractBaseEntity {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;

    @NotNull
    @Column(name = "creation_date")
    private OffsetDateTime creationDate;

    public AbstractBaseEntity(Clock clock) {
        Objects.requireNonNull(clock, "Entity needs clock to set creation date");
        this.creationDate = OffsetDateTime.now(clock);
    }

}
