package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.domain.kernel.AbstractBaseDomainEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.str.shootingresulttracker.kernel.StringUtils.requiredNonEmpty;

@Getter
@Entity
@Table(name = "srt_weapon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Weapon extends AbstractBaseDomainEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WeaponType type;

    @NotNull
    @Column(name = "caliber")
    @Enumerated(EnumType.STRING)
    private Caliber caliber;

    @NotNull
    @Column(name = "model")
    private String model;

    @NotNull
    @Column(name = "production_date")
    private OffsetDateTime productionDate;

    @NotNull
    @Column(name = "purchase_date")
    private OffsetDateTime purchaseDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "magazine_id")
    private Magazine magazine;

    public Weapon(
            String name,
            WeaponType type,
            Caliber caliber,
            String model,
            OffsetDateTime productionDate,
            OffsetDateTime purchaseDate,
            UUID createdBy,
            Clock clock) {

        super(clock, createdBy);
        requiredNonEmpty(name, "Weapon name");
        Objects.requireNonNull(type, "Weapon type can not be null");
        Objects.requireNonNull(caliber, "Weapon caliber can not be null");
        requiredNonEmpty(model, "Weapon model");

        this.name = name;
        this.type = type;
        this.caliber = caliber;
        this.model = model;
        this.productionDate = productionDate;
        this.purchaseDate = purchaseDate;
    }

    public void setMagazine(Magazine magazine) {
        Objects.requireNonNull(magazine, "Weapon can not be link to null magazine");
        this.magazine = magazine;
    }
}
