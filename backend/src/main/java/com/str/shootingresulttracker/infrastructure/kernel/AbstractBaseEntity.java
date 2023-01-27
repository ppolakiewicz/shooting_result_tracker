package com.str.shootingresulttracker.infrastructure.kernel;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Id
    @Column(name = "id")
    public UUID id;

    public AbstractBaseEntity() {
        id = UUID.randomUUID();
    }
}
