package com.str.shootingresulttracker.weapon;

import com.str.shootingresulttracker.kernel.AbstractBaseEntity;
import com.str.shootingresulttracker.magazine.Magazine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "srt_weapon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Weapon extends AbstractBaseEntity {

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
            Clock clock) {

        super(clock);
        Objects.requireNonNull(name, "Weapon name can not be null");
        Objects.requireNonNull(type, "Weapon type can not be null");
        Objects.requireNonNull(caliber, "Weapon caliber can not be null");
        Objects.requireNonNull(model, "Weapon model can not be null");
        Objects.requireNonNull(productionDate, "Weapon production date can not be null");
        Objects.requireNonNull(purchaseDate, "Weapon purchase date can not be null");

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
