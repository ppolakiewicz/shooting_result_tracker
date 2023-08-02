package com.str.shootingresulttracker.weapon;

import com.str.shootingresulttracker.kernel.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@Entity
@Table("weapon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeaponEntity extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WeaponType type;

    @Column(name = "caliber")
    @Enumerated(EnumType.STRING)
    private Caliber caliber;

    @Column(name = "model")
    private String model;

    @Column(name = "production_date")
    private OffsetDateTime productionDate;

    @Column(name = "purchase_date")
    private OffsetDateTime purchaseDate;

    public WeaponEntity(String name, WeaponType type, Caliber caliber, String model, OffsetDateTime productionDate, OffsetDateTime purchaseDate) {
        this.name = name;
        this.type = type;
        this.caliber = caliber;
        this.model = model;
        this.productionDate = productionDate;
        this.purchaseDate = purchaseDate;
    }
}
